package com.pt.chat.domain.useCase

import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.mapper.toEntity
import com.pt.chat.domain.model.Attachment
import com.pt.core.utils.BaseUseCase

class SaveAttachmentsUseCase(
    private val repository: IChatRepository
) : BaseUseCase<Pair<Int, List<Attachment>>, Unit> {
    override suspend fun execute(params: Pair<Int, List<Attachment>>) {
        val (messageId, attachments) = params
        val attachmentEntities = attachments.map { it.toEntity(messageId) }
        repository.saveAttachments(attachmentEntities)
    }
}