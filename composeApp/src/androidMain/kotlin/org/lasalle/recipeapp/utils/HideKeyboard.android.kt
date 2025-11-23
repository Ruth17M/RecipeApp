package org.lasalle.recipeapp.utils

import androidx.compose.ui.focus.FocusManager

actual fun hideKeyboard(focusManager: FocusManager) {
    focusManager.clearFocus()
}