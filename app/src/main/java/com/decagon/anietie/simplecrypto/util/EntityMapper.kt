package com.decagon.anietie.simplecrypto.util

interface EntityMapper<Entity, Domain> {

    fun mapFromEntity(entity: Entity): Domain
}
