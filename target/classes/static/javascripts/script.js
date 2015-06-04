var domain = 'http://localhost:8080/musics/';

$(function(){

	// $('.audio-bullet').audiobullet();
	$('.recommend-music .audio-bullet').audiobullet();

	activateAudioBullet();

	activateSearch();
})

function activateAudioBullet() {
	var showPopover = function($root, obj) {
		var $anchor = $root.find('.bullet-anchor[anchor-timestamp="'+obj.currentTime+'"]');
		var offset = $anchor.css('left');
		$root.find('.bullet-popover').css('left', offset);
		if (!$anchor.length) return;

		setTimeout(function(){
			$root.find('.bullet-popover').removeClass('bullet-popover-hide');
		},250)

		$root.find('.bullet-popover').text($anchor.data('bullet').text);
	}

	var hidePopover = function($root) {
		$root.find('.bullet-popover').addClass('bullet-popover-hide');
	}

	$('.audio-bullet').one('audio:play', function(){
		$(this).closest('.music-widget').find('.bullet-form').slideDown();
	})

	$('body').on('audio:timeUpdate', '.audio-bullet', function(e, obj) {
		// console.log(obj.currentTime);
		showPopover($(this).closest('.music-widget'), obj);
	})

	$('body').on('bulletAdded','.audio-bullet', function(e, obj, board){
		// $('pre').text(arr);
		console.log(board);
	})

	$('body').on('hasBullet', '.audio-bullet', function(e, arr){
		// var $panel = $('.bullet-panel');
		// $panel.empty();

		// $.each(arr, function(){

		// 	var $bullet = $('<p class="bullet"></p>');
		// 	$bullet.text(this.uname + ": " + this.text);
		// 	$panel.append($bullet);
		// })
	})

	$('body').on('audio:change','.audio-bullet', function() {
		// var $panel = $(this).closest(".music-widget").find('.bullet-panel');
		// $panel.empty();
		hidePopover($(this).closest('.music-widget'));
	})

	$('body').on('submit','.bullet-form', function(){
		var uname = 'me';
		var obj = {};
		obj.uname = uname;
		obj.text = $(this).find('input').val();

		$(this).closest('.music-widget').find('.audio-bullet').audiobullet('add', obj);
		$(this).find('input').val("");
		return false;
	})	

	//preload comment
	$('body').on('buildSuccess', '.recommend-music .audio-bullet', function(){
		var url = domain;
		var $audio = $(this);
		var id = $audio.closest('.music-widget').attr('data-id');
		$.get(url+id, function(data){

			// console.log(data);
			var comments = data.bulletComments;
			
			comments.forEach(function(comment){
				comment.preload = true;
				comment.text = comment.content;
				comment.timestamp = comment.timeStamp;
				delete comment.timeStamp;
				delete comment.content;
				$audio.audiobullet('add', comment);
			})
		})
	})


	//save comment
	$('body').on('beforeBulletAdded','.audio-bullet', function(e, obj){
		var url = domain;
		var data = {};
		// data.id = 1;
		data.timeStamp = obj.rawTime;
		data.user = null;
		data.content = obj.text;
		var musicId = $(this).closest('.music-widget').attr("data-id");
		url += musicId + "/addComment/";
		$.ajax({
			url: url,
			crossDomain:true,
			type: 'POST',
			data: JSON.stringify(data),
			contentType:'application/json',
			// dataType: 'text',
			success: function(d) {
				console.log(d);
			},
			error: function() {
				console.dir(arguments);
			}
		})

	})

	// $('body').on('mouseenter','.bullet-anchor', function(){
	// 	var $bullet = $('<span></span>');
	// 	// $bullet.css("position", "absolute").css("left", "")
	// 	$bullet.css({
	// 		position: "absolute",
	// 		left: "3px",
	// 		bottom: 0,
	// 	})
	// 	$bullet.text($(this).attr('anchor-bullet'));
	// 	var _this = this;
	// 	setTimeout(function(){
	// 		$(_this).append($bullet);
	// 	},100)

	// })

	// $('body').on('mouseout','.bullet-anchor', function(){
	// 	$(this).empty();
	// })

}



function activateSearch() {


	$('.search-form').on('submit', function(){
		var keyword = $(this).find('input').val();
		var url = $(this).attr('action') + keyword;

		$.get(url, function(data){

			var $musics = [];
			console.log(data);
			data.forEach(function(obj){
				$musics.push(buildMusic(obj));
			})
			// console.log($musics);
			$('.recommend .music-widget').fadeOut(500, function(){
				$(this).remove();
				$musics.forEach(function($music){
					$('.recommend .music-append').append($music);
				})
			})


		})


		return false;
	})
}

function buildMusic(obj) {


	var $clone = $('.clone-music').clone().removeClass('clone-music hidden');

	$clone.find('.music-title').text(obj.title);
	$clone.find('.music-artist').text(obj.artist);
	$clone.attr('data-id', obj.id);
	$clone.find('audio source').attr('src', obj.sourceURL);

	$clone.css('padding-bottom', '40px');
	$clone.css('margin-bottom', '30px');
	$clone.css('border-bottom', '1px solid #e4e9f0')
	$clone.find('.audio-bullet').audiobullet();


	//preload comment
	$clone.find('.audio-bullet').on('buildSuccess', function(){
		var $audio = $(this);
		// console.log(data);
		var comments = obj.bulletComments;
		console.log(obj);
		if (comments == null) return;

		comments.forEach(function(comment){
			comment.preload = true;
			comment.text = comment.content;
			comment.timestamp = comment.timeStamp;
			delete comment.timeStamp;
			delete comment.content;
			$audio.audiobullet('add', comment);
		})
	})


	$clone.find('.audio-bullet').one('audio:play', function(){
		$(this).closest('.music-widget').find('.bullet-form').slideDown();
	})

	return $clone;
}












