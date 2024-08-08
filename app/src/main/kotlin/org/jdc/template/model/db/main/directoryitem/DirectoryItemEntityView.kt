package org.jdc.template.model.db.main.directoryitem

import androidx.room.DatabaseView
import org.jdc.template.model.domain.inline.FirstName
import org.jdc.template.model.domain.inline.IndividualId
import org.jdc.template.model.domain.inline.LastName
import org.jdc.template.model.domain.inline.ProfilePicture

@DatabaseView(
    viewName = DirectoryItemEntityView.VIEW_NAME,
    value = DirectoryItemEntityView.VIEW_QUERY
)
data class DirectoryItemEntityView(
    val individualId: IndividualId,
    val firstName: FirstName?,
    val lastName: LastName?,
    val profilePicture: ProfilePicture?
) {
    fun getFullName() = "${firstName?.value.orEmpty()} ${lastName?.value.orEmpty()}"

    companion object {
        const val VIEW_NAME = "DirectoryItem"
        const val VIEW_QUERY = "SELECT id AS individualId, lastName, firstName, profilePicture FROM Individual"
    }
}