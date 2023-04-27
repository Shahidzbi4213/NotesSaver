package com.example.noterssaver.presentation.view.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
 fun CustomIconButton(
    modifier: Modifier = Modifier,
    icon: Any,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, modifier = modifier)
    {
        when (icon) {
            is Int -> Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription
            )

            is ImageVector -> Icon(imageVector = icon, contentDescription = contentDescription)
            else -> Icon(bitmap = icon as ImageBitmap, contentDescription = contentDescription)
        }
    }
}