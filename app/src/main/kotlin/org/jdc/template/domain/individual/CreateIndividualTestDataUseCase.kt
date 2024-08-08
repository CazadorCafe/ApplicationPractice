package org.jdc.template.domain.individual

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.jdc.template.inject.DefaultDispatcher
import org.jdc.template.model.domain.Household
import org.jdc.template.model.domain.Individual
import org.jdc.template.model.domain.inline.Affiliation
import org.jdc.template.model.domain.inline.FirstName
import org.jdc.template.model.domain.inline.LastName
import org.jdc.template.model.domain.inline.Phone
import org.jdc.template.model.domain.inline.ProfilePicture
import org.jdc.template.model.domain.type.IndividualType
import org.jdc.template.model.repository.IndividualRepository
import javax.inject.Inject

class CreateIndividualTestDataUseCase
@Inject constructor(
    private val individualRepository: IndividualRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() = withContext(defaultDispatcher) {
        // clear any existing items
        individualRepository.deleteAllIndividuals()

        val household1 = Household(
            name = LastName("Campbell")
        )

        val individual1 = Individual(
            householdId = household1.id,
            firstName = FirstName("Jeff"),
            lastName = LastName("Campbell"),
            phone = Phone("801-555-0000"),
            individualType = IndividualType.HEAD,
            birthDate = LocalDate(1970, 1, 1),
            alarmTime = LocalTime(7, 0),
        )

        val individual1a = Individual(
            householdId = household1.id,
            firstName = FirstName("Ty"),
            lastName = LastName("Campbell"),
            phone = Phone("801-555-0001"),
            individualType = IndividualType.CHILD,
            birthDate = LocalDate(1970, 1, 1),
            alarmTime = LocalTime(7, 0),
        )

        val household2 = Household(
            name = LastName("Miller")
        )

        val individual2 = Individual(
            householdId = household2.id,
            firstName = FirstName("John"),
            lastName = LastName("Miller"),
            phone = Phone("303-555-1111"),
            individualType = IndividualType.HEAD,
            birthDate = LocalDate(1970, 1, 2),
            alarmTime = LocalTime(6, 0),
        )

        val household3 = Household(
            name = LastName("Skywalker")
        )

        val individual3 = Individual(
            householdId = household3.id,
            firstName = FirstName("Luke"),
            lastName = LastName("Skywalker"),
            birthDate = LocalDate(1963, 5,5),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/07.png"),
            forceSensitive = true,
            affiliation = Affiliation("RESISTANCE")
        )

        val household4 = Household(
            name = LastName("Organa Solo")
        )

        val individual4 = Individual(
            householdId = household3.id,
            firstName = FirstName("Leia"),
            lastName = LastName("Organa"),
            birthDate = LocalDate(1963, 5,5),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/06.png"),
            forceSensitive = true,
            affiliation = Affiliation("RESISTANCE")
        )

        val individual4a = Individual(
            householdId = household3.id,
            firstName = FirstName("Han"),
            lastName = LastName("Solo"),
            birthDate = LocalDate(1956, 8,22),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/04.png"),
            forceSensitive = false,
            affiliation = Affiliation("RESISTANCE")
        )

        val individual4b = Individual(
            householdId = household3.id,
            firstName = FirstName("Kylo"),
            lastName = LastName("Ren"),
            birthDate = LocalDate(1987, 10,31),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/05.jpg"),
            forceSensitive = true,
            affiliation = Affiliation("FIRST_ORDER")
        )

        val household5 = Household(
            name = LastName("Chewie")
        )

        val individual5 = Individual(
            householdId = household3.id,
            firstName = FirstName("Chewbacca"),
            lastName = LastName(""),
            birthDate = LocalDate(1782, 11,15),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/01.png"),
            forceSensitive = false,
            affiliation = Affiliation("RESISTANCE")
        )

        val household6 = Household(
            name = LastName("Snoke")
        )

        val individual6 = Individual(
            householdId = household3.id,
            firstName = FirstName("Supreme Leader"),
            lastName = LastName("Snoke"),
            birthDate = LocalDate(1947, 1,1),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/08.jpg"),
            forceSensitive = true,
            affiliation = Affiliation("FIRST_ORDER")
        )

        val household7 = Household(
            name = LastName("Hux")
        )

        val individual7 = Individual(
            householdId = household3.id,
            firstName = FirstName("General"),
            lastName = LastName("Hux"),
            birthDate = LocalDate(1982, 7,4),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/03.png"),
            forceSensitive = false,
            affiliation = Affiliation("FIRST_ORDER")
        )

        val household8 = Household(
            name = LastName("Vader")
        )

        val individual8 = Individual(
            householdId = household3.id,
            firstName = FirstName("Darth"),
            lastName = LastName("Vader"),
            birthDate = LocalDate(1947, 7,13),
            profilePicture = ProfilePicture("https://edge.ldscdn.org/mobile/interview/02.jpg"),
            forceSensitive = false,
            affiliation = Affiliation("SITH")
        )

        individualRepository.saveNewHousehold(household1, listOf(individual1, individual1a))
        individualRepository.saveNewHousehold(household2, listOf(individual2))
        individualRepository.saveNewHousehold(household4, listOf(individual4, individual4a, individual4b))
        individualRepository.saveNewHousehold(household5, listOf(individual5))
        individualRepository.saveNewHousehold(household6, listOf(individual6))
        individualRepository.saveNewHousehold(household7, listOf(individual7))
        individualRepository.saveNewHousehold(household8, listOf(individual8))

    }
}
