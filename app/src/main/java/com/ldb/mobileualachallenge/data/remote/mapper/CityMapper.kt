package com.ldb.mobileualachallenge.data.remote.mapper

import com.ldb.mobileualachallenge.data.remote.dto.CityRemoteDto
import com.ldb.mobileualachallenge.domain.model.City

fun CityRemoteDto.toDomain(): City = City(id)