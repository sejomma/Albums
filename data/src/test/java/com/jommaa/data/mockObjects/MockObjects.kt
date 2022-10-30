package com.jommaa.data.mockObjects

import com.jommaa.data.dataSource.local.entities.AlbumEntity
import com.jommaa.data.dto.AlbumDto
import com.jommaa.domain.entities.Album
import java.util.*

class MockObjects {

    companion object {
        fun fakeAlbumDtoObject():
                AlbumDto {
            return AlbumDto(
                albumId = 1,
                id = 1,
                title = "accusamus beatae ad facilis cum similique qui sunt",
                url = " https://via.placeholder.com/600/92c952",
                thumbnailUrl = "https//via.placeholder.com/150/92c952"
            )
        }

        fun fakeAlbumEntityObject():
                AlbumEntity {
            return AlbumEntity(
                albumId = 1,
                id = 1,
                title = "accusamus beatae ad facilis cum similique qui sunt",
                url = " https://via.placeholder.com/600/92c952",
                thumbnailUrl = "https//via.placeholder.com/150/92c952"
            )
        }

        fun fakeAlbumObject():
                Album {
            return Album(
                albumId = 1,
                id = 1,
                title = "accusamus beatae ad facilis cum similique qui sunt",
                url = " https://via.placeholder.com/600/92c952",
                thumbnailUrl = "https//via.placeholder.com/150/92c952"
            )
        }

        fun fakeAlbumsDtoList(): List<AlbumDto> {
            return Arrays.asList(fakeAlbumDtoObject())
        }
    }
}