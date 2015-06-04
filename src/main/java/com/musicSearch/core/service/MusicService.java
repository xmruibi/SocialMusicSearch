package com.musicSearch.core.service;

import java.util.List;

import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import com.musicSearch.core.domain.BulletComment;
import com.musicSearch.core.domain.Music;
import com.musicSearch.core.domain.User;
import com.musicSearch.core.repository.MusicRepository;

@Service
public class MusicService {

	private final MusicRepository musicRepository;

	@Autowired
	public MusicService(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}

	public Music save(Music music) {
		return musicRepository.save(music);
	}

	public List<Music> saveAll(List<Music> musics) {
		return musicRepository.save(musics);
	}

	public void saveBulletComment(String id, BulletComment bulletComment) {
		Music music = musicRepository.findOne(id);
		music.addBulletComment(bulletComment);
		musicRepository.save(music);
	}

	public Music findById(String id) {
		return musicRepository.findOne(id);
	}

	public List<Music> findAllMusic() {
		return musicRepository.findAll();
	}

	public void deleteById(String id) {
		musicRepository.delete(id);
	}
}
