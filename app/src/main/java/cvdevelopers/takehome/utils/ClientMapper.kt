package cvdevelopers.takehome.utils

import cvdevelopers.takehome.data.ClientTable
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.ui.ClientView

fun Client.toTableItem(): ClientTable {
    return ClientTable(
        email,
        id.name,
        id.value.orEmpty(),
        name.first,
        name.last,
        name.title,
        picture.large,
        picture.medium,
        picture.thumbnail
    )
}

fun ClientTable.toView(): ClientView {
    return ClientView(firstName, lastName, mediumPicture)
}