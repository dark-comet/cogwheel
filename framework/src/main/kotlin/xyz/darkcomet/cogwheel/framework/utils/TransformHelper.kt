package xyz.darkcomet.cogwheel.framework.utils

import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import kotlin.reflect.KProperty1

/*
    This file contains shared functions to facilitate the transform of serialization objects fields 
    to domain model equivalents.
 */

internal fun <TObject, TValue> require(
    obj: TObject,
    property: KProperty1<TObject, MaybeAbsent<TValue>?>
): TValue? {
    val field = property.get(obj)
        ?: throw InvalidModelException("Required object field '${property.name}' is absent");
    
    return field.value
}

internal fun <TObject, TValue> requireNonNull(
    obj: TObject,
    property: KProperty1<TObject, MaybeAbsent<TValue>?>
): TValue {
    val field = property.get(obj)
    if (field == null || field.value == null) {
        throw InvalidModelException("Required object field '${property.name}' has value null")
    }
    return field.value!!
}

internal fun <TObject, TValue> requireNonNullIfPresent(
    obj: TObject,
    property: KProperty1<TObject, MaybeAbsent<TValue>?>
): TValue? {
    val field = property.get(obj) ?: return null
    if (field.value == null) {
        throw InvalidModelException("Required object field '${property.name}' is present but is null")
    }
    return field.value!!
}