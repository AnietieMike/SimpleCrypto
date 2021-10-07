package com.decagon.anietie.simplecrypto.util

/**
 * An interface to map network models into domain models
* */
interface EntityMapper<Entity, Domain> {

    fun mapFromEntity(entity: Entity): Domain
}
