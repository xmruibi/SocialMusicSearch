package com.musicSearch.core.converter;

import java.util.ArrayList;
import java.util.List;

import com.musicSearch.core.domain.Music;
import com.musicSearch.core.index.domain.IndexedMusic;

/**
 * This is utility class for convert music object from mongodb to indexing music in elasticsearch by indexedmuisc objecy
 * @author ruibi
 *
 */
public class MusicIndexingConverter {

	public static IndexedMusic convert(Music music) {
		return new IndexedMusic(music.getId(), music.getTitle(),
				music.getArtist(), music.getDescription(),
				music.getOriginTags(), music.getBulletComments());
	}

	public static List<IndexedMusic> collectionConvert(List<Music> musics) {
		List<IndexedMusic> indexedMusics = new ArrayList<IndexedMusic>();
		for (Music music : musics)
			indexedMusics.add(convert(music));
		return indexedMusics;
	}
}
