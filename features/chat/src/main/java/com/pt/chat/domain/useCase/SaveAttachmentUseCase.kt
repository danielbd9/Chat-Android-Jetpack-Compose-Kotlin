package com.pt.chat.domain.useCase

import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.mapper.toEntity
import com.pt.chat.domain.model.Attachment
import com.pt.core.utils.BaseUseCase

class SaveAttachmentsUseCase(
    private val repository: IChatRepository
) : BaseUseCase<List<Attachment>, Unit> {
    override suspend fun execute(params: List<Attachment>) {
        val attachmentEntities = params.map { it.toEntity() }
        repository.saveAttachments(attachmentEntities)
    }
}