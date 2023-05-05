package com.example.noterssaver.data.source.local.converters

import androidx.room.TypeConverter
import com.example.noterssaver.presentation.setting.util.OrderType
import com.example.noterssaver.presentation.setting.util.ThemeStyle

/*
 * Created by Shahid Iqbal on 5/5/2023.
 */

class OrderTypeConverter {

    @TypeConverter
    fun fromOrderType(order: OrderType): String {
        return order.name
    }

    @TypeConverter
    fun toOrderType(orderTypeName: String): OrderType {
        return try {
            OrderType.valueOf(orderTypeName)
        } catch (ex: IllegalArgumentException) {
            OrderType.TIME // Default value if the stored value is invalid
        }
    }
}