package com.zzekak.domain.address.entity

import com.zzekak.domain.address.entity.AppointmentAddressEntity.Companion.TABLE_ADDRESS
import com.zzekak.domain.common.entity.AuditableBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = TABLE_ADDRESS)
class AppointmentAddressEntity(
    @Id @Column(name = APPOINTMENT_ADDRESS_ID) @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,
    @Column(name = CITY_OR_PROVINCE)
    val cityOrProvince: String,
    @Column(name = DISTRICT_OR_CITY)
    val districtOrCity: String,
    @Column(name = POSTAL_CODE)
    val postalCode: String,
    @Column(name = JIBUN_ADDRESS)
    val jibunAddress: String,
    @Column(name = ROAD_ADDRESS)
    val roadAddress: String,
    @Column(name = COORDINATE_X)
    val x: String,
    @Column(name = COORDINATE_Y)
    val y: String,
) : AuditableBase() {
    companion object {
        const val TABLE_ADDRESS = "appointment_address"
        const val APPOINTMENT_ADDRESS_ID = "appointment_address_id"
        const val CITY_OR_PROVINCE = "city_or_province"
        const val DISTRICT_OR_CITY = "district_or_city"
        const val POSTAL_CODE = "postal_code"
        const val JIBUN_ADDRESS = "jibun_address"
        const val ROAD_ADDRESS = "road_address"
        const val COORDINATE_X = "x"
        const val COORDINATE_Y = "y"
    }
}
