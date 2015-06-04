(function($){

	"use strict";

	/*
	 *	Constructor function
	 *  event:  bulletAdded, beforeBulletAdded, bulletRemoved, beforeBulletRemoved, hasBullet
	 */


	function AudioBullet(element) {

		this.bulletArray = [];
		this.bulletLimit = 10;
		this.bulletBoard = {};

		this.isAnchorShown = true;

		this.latency = 3; // 3 seconds

		this.onSecond = -1;

		this.music = element;
		this.$element = $(element);
		this.player = new AudioPlayer(element);

		this.build();



	}


	AudioBullet.prototype = {


		build: function() {
			this.registerEvent();
		},

		add: function(obj) {

			var o = {};

			o.toString = function() {
				return this.timestamp + 's: ' + this.text;
			}

			if (typeof obj == 'string') {
				o.text = obj;
			} else { //is object
				o = $.extend(o, obj);
			}

			o.timestamp = Math.floor(o.timestamp) || Math.floor(this.music.currentTime);
			o.rawTime = o.rawTime || this.music.currentTime;	
			o.preload = o.preload || false;	
			o.text = o.text || "";

			if (!o.preload)
				this.$element.trigger('beforeBulletAdded', [o]);

			this.bulletBoard[o.timestamp] = this.bulletBoard[o.timestamp] || [];
			this.bulletBoard[o.timestamp].push(o);

			// this.bulletArray.push(o);
			if (!o.preload) {
				this.$element.trigger('bulletAdded', [o, this.bulletBoard]);
				this.resetSecond(); // -1
				this.$element.trigger('audio:timeUpdate', [{rawTime: o.rawTime, currentTime: o.timestamp}]); //update bullet				
			}


			this.addAnchor(o);

			return o;

		},

		addAnchor: function(obj) {

			var $anchor = $('<div class="bullet-anchor"></div>');
			$anchor.data('bullet', obj);
			if (!this.isAnchorShown)
				$anchor.css('visibility', 'hidden');
			if (this.bulletBoard[obj.timestamp].length != 1) return;

			var percent = this.getOffset(obj.timestamp);

			$anchor.css('position', 'absolute');
			$anchor.css('left', percent + 'px');
			$anchor.attr('anchor-timestamp', obj.timestamp);
			$anchor.data('bullet', obj);
			this.player.$timeline.append($anchor);
		},


		getOffset: function(time) {

			return this.player.timelineWidth * (time / this.player.duration);

		},

		remove: function(obj) {

		},

		resetSecond: function() {
			this.onSecond = -1;
		},

		registerEvent: function() {

			var _this = this;


			//show bullet
			_this.$element.on('audio:timeUpdate', function(e, time){

				if (_this.bulletBoard[time.currentTime] && _this.bulletBoard[time.currentTime].length) {

					if (_this.onSecond != time.currentTime) {
						console.dir(_this.bulletBoard[time.currentTime]);
						_this.$element.trigger('hasBullet', [_this.bulletBoard[time.currentTime]]); //arr
						_this.onSecond = time.currentTime;
					}
				}
			})

			_this.$element.on('audio:start', function(){
				_this.resetSecond();
			})
			_this.$element.on('audio:moveRunner',function(){
				_this.resetSecond();
			})
			_this.$element.on('audio:clickLine',function(){
				_this.resetSecond();				
			})



		},



		addBulletToArray: function(bullet) {

			if (this.bulletArray == this.bulletLimit)
				this.bulletArray.shift();

			this.bulletArray.push(bullet);

		},


		// getter
		bullets: function() {

			return this.bulletArray;

		},

		bulletsBoard: function() {

			return this.bulletBoard;

		},

		// getter
		duration: function() {

			return this.player.duration;

		},
		// getter
		currentTime: function() {

			return this.music.currentTime;

		},

		// getter
		audio: function() {

			return this.music;

		},

		config: function() {

		}



	}



	function AudioPlayer(element) {

		var _this = this;


		this.music = element;
		this.duration = 0; // get it later
		this.$element = $(element);
		this.$container = $('<div class="bullet-player"></div>');

		this.$playBtn = null;
		this.$timeline = null;
		this.$timelinePassed = null;
		this.$runner = null;
		this.timelineWidth = 0;

		this.$timePanel = null;
		this.$totalTimePanel = null;

		this.isMoving = false;

		this.build();

		this.isLoad = false;

		// get duration
		this.$totalTimePanel.text("00:00");
		this.$element.on('canplaythrough', function(){

			if (!_this.isLoad) {
				_this.duration = _this.music.duration;
				_this.$totalTimePanel.text(_this.toHHMMSS(_this.duration));		
				_this.$element.trigger('buildSuccess');		
				_this.isLoad = true;
			}

		})



	}

	/*
	 *	player prototype function
	 *	event: audio:moveRunner, audio:timeUpdate, audio:clickLine, audio:play, audio:pause, audio:start, audio:end, audio:change
	 */	
	AudioPlayer.prototype = {

		constructor: AudioPlayer,

		build: function() {

			// build player widget
			this.buildWidget(); 
			// register event
			this.registerEvent();
		},

		buildWidget: function() {
			this.$playBtn = $('<button class="play-btn play"></button>');
			this.$timeline = $('<div class="play-timeline">');
			this.$timelinePassed = $('<div class="play-timeline-passed">');
			this.$timePanel = $('<div class="time-panel">00:00</div>');
			this.$totalTimePanel = $('<div class="total-time-panel">');
			this.$runner = $('<div class="play-runner"></div>');

			this.$timeline.append(this.$timelinePassed).append(this.$runner).append('<div class="bullet-popover bullet-popover-hide">');
			this.$container.append(this.$playBtn).append(this.$timePanel).append(this.$timeline).append(this.$totalTimePanel);
			this.$element.after(this.$container);
			this.$container.append(this.$element);

			// this.timelineWidth = this.$timeline.width() - this.$runner.width();
			this.timelineWidth = this.$timeline.width();
		},

		moveRunner: function(e) {
			var _this = this;
			var newMargLeft = e.pageX - _this.$timeline.offset().left;

			_this.$element.trigger('audio:moveRunner', [{rawTime: _this.music.currentTime, currentTime: Math.floor(_this.music.currentTime), offset: newMargLeft}]);

			_this.$element.trigger('audio:change');

			if (newMargLeft >= 0 && newMargLeft <= _this.timelineWidth) {
				_this.$runner.css('margin-left', newMargLeft + "px");
			}
			if (newMargLeft < 0) {
				_this.$runner.css('margin-left', 0);
			}
			if (newMargLeft > _this.timelineWidth) {
				_this.$runner.css('margin-left', _this.timelineWidth + "px");
			}
		},

		timeUpdate: function() {
			var _this = this;

			if (_this.isMoving) return; // turn off

			if (Math.floor(_this.music.currentTime) == 0)
				_this.$element.trigger('audio:start').trigger('audio:change');

			// trigger jquery event
			_this.$element.trigger('audio:timeUpdate', [{
				rawTime: _this.music.currentTime, 
				currentTime: Math.floor(_this.music.currentTime),
				formatTime: _this.toHHMMSS(_this.music.currentTime)
			}]);

			//change time
			_this.$timePanel.text(_this.toHHMMSS(Math.floor(_this.music.currentTime)));

			var playPercent = _this.timelineWidth * (_this.music.currentTime / _this.duration);
			_this.$runner.css('margin-left', playPercent + 'px');
			_this.$timelinePassed.css('width', playPercent + 'px');
			if (_this.music.currentTime == _this.duration) {
				//pause
				_this.$element.trigger('audio:end');
				_this.pause();
			}
		},
		mouseDown: function() {
			var _this = this;
			_this.isMoving = true;
			_this.$container.on('mousemove', _this.moveRunner.bind(_this));

			// _this.$element.off('timeupdate', _this.timeUpdate.bind(_this)); //temperory remove update effect
		},

		mouseUp: function(e) {

			var _this = this;
			if (_this.isMoving == true) {
				_this.moveRunner.call(_this, e);
				_this.$container.off('mousemove');
				// change current time
				_this.music.currentTime = _this.duration * _this.clickPercent.call(_this, e);
				// _this.$element.on('timeupdate', _this.timeUpdate.bind(_this));
			}
			_this.isMoving = false;
		},


		clickPercent: function(e) {
			var _this = this;
			return (e.pageX - _this.$timeline.offset().left) / _this.timelineWidth;
		},

		registerEvent: function() {

			var _this = this;

			_this.$playBtn.on('click', _this.togglePlay.bind(_this));

			_this.$element.on('timeupdate', _this.timeUpdate.bind(_this));

			_this.$timeline.on('click', function(e) {
				_this.$element.trigger('audio:clickLine');
				_this.$element.trigger('audio:change');
				_this.moveRunner.call(_this, e);
				_this.music.currentTime = _this.duration * _this.clickPercent.call(_this, e);				
			});

			_this.$runner.on('mousedown', _this.mouseDown.bind(_this));
			$(window).on('mouseup', _this.mouseUp.bind(_this));

		},


		togglePlay: function() {
			var _this = this;
			if (_this.music.paused) {
				_this.play();
			} else { // pause music
				_this.pause();
			}
		},

		play: function() {
			var _this = this;

			_this.$element.trigger('audio:play', [{rawTime: _this.music.currentTime, currentTime: Math.floor(_this.music.currentTime)}]);
			_this.music.play();
			// remove play, add pause
			_this.$playBtn.removeClass('play').addClass('pause');
		},

		pause: function() {
			var _this = this;
			_this.$element.trigger('audio:pause', [{rawTime: _this.music.currentTime, currentTime: Math.floor(_this.music.currentTime)}]);
			_this.music.pause();
			// remove pause, add play
			_this.$playBtn.removeClass('pause').addClass('play');
			
		},

		reset: function() {

		},

		replay: function() {
			var _this = this;
			_this.reset();
			_this.play();
		},

		toHHMMSS: function (second) {
			var sec_num = parseInt(second, 10); // don't forget the second param
			var hours   = Math.floor(sec_num / 3600);
			var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
			var seconds = sec_num - (hours * 3600) - (minutes * 60);

			// if (hours   < 10) {hours   = "0"+hours;}
			if (minutes < 10) {minutes = "0"+minutes;}
			if (seconds < 10) {seconds = "0"+seconds;}
			var time    = hours == 0 ? minutes+':'+seconds : hours+':'+minutes+':'+seconds;
			return time;
		}
	}

	$.fn.audiobullet = function(arg1, arg2, arg3) {

		var results = [];

		this.each(function(){

			var audiobullet = $(this).data('audiobullet');

			if (!audiobullet) {
				console.log('init');
				audiobullet = new AudioBullet(this);
				//add flag
				$(this).data('audiobullet', audiobullet);
				results.push(audiobullet);

			} else if (!arg2 && !arg3) {
				console.log('one arg');
				//return value or config
				if (typeof arg1 == 'string') {
					
					results.push(audiobullet[arg1]());

				} else if (typeof arg1 == 'object') {

					audiobullet.config(arg1);
					results.push(audiobullet);

				} 

			} else {
				console.log('two args');
				//operation
				var retVal = audiobullet[arg1](arg2, arg3);
				if (retVal) results.push(retVal);
			}

		});

		if (arguments.length <= 1)
			return results.length > 1 ? results : results[0];
		else
			return results;
	}


	// $('.audio-bullet').audiobullet();

})(window.jQuery);







