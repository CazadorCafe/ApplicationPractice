package org.jdc.template.model.db.main.individual

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.jdc.template.model.domain.inline.Affiliation
import org.jdc.template.model.domain.inline.Email
import org.jdc.template.model.domain.inline.FirstName
import org.jdc.template.model.domain.inline.HouseholdId
import org.jdc.template.model.domain.inline.IndividualId
import org.jdc.template.model.domain.inline.LastName
import org.jdc.template.model.domain.inline.Phone
import org.jdc.template.model.domain.inline.ProfilePicture
import org.jdc.template.model.domain.type.IndividualType

@Entity("Individual")
data class IndividualEntity(
    @PrimaryKey
    val id: IndividualId,
    val householdId: HouseholdId?,
    val individualType: IndividualType,
    val firstName: FirstName?,
    val lastName: LastName?,
    val birthDate: LocalDate?,
    val alarmTime: LocalTime?,
    val phone: Phone?,
    val email: Email?,
    val available: Boolean,
    val profilePicture: ProfilePicture?,
    val forceSensitive: Boolean?,
    val affiliation: Affiliation?,

    val created: Instant,
    val lastModified: Instant
)
