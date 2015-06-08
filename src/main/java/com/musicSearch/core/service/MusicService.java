package com.musicSearch.core.service;

import java.util.List;

import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import com.musicSearch.core.domain.BulletComment;
import com.musicSearch.core.domain.Music;
import com.musicSearch.core.domain.User;
import com.musicSearch.core.repository.BulletCommentRepository;
import com.musicSearch.core.repository.MusicRepository;

/**
 * This class concluded all basic CRUD method for music object
 * @author ruibi
 *
 */
@Service
public class MusicService {

	@Autowired
	private  MusicRepository musicRepository;

	@Autowired
	private BulletCommentRepository bulletCommentRepository;

	/**
	 * Save one music
	 * @param music
	 * @return
	 */
	public Music save(Music music) {
		return musicRepository.save(music);
	}

	/**
	 * Music batch Saving
	 * @param musics
	 * @return
	 */
	public List<Music> saveAll(List<Music> musics) {
		return musicRepository.save(musics);
	}

	/**
	 * Save bullet comment
	 * @param id
	 * @param bulletComment
	 */
	public void saveBulletComment(String id, BulletComment bulletComment) {
		Music music = musicRepository.findOne(id);
		music.addBulletComment(bulletComment);
		System.out.println(bulletComment.toString());
		bulletCommentRepository.save(bulletComment);
		musicRepository.save(music);
	}

	/**
	 * Find a music by id
	 * @param id
	 * @return
	 */
	public Music findById(String id) {
		return musicRepository.findOne(id);
	}

	/**
	 * Find all music in music collection
	 * @return
	 */
	public List<Music> findAllMusic() {
		return musicRepository.findAll();
	}

	/**
	 * delete a music by id
	 * @param id
	 */
	public void deleteById(String id) {
		musicRepository.delete(id);
	}
}
