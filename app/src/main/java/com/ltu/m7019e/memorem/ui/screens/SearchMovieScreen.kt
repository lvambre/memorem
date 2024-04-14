package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ltu.m7019e.memorem.R

@Composable
fun SearchMovieScreen(
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = "",
        label = {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.bodyMedium
            ) },
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
        onValueChange = { }
    )
}