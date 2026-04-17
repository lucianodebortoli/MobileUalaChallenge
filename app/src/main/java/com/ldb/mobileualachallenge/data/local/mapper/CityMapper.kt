package com.ldb.mobileualachallenge.data.local.mapper

import com.ldb.mobileualachallenge.data.local.dto.CityLocalDto
import com.ldb.mobileualachallenge.domain.model.City

fun CityLocalDto.toDomain(): City = City(id)