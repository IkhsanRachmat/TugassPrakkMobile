package id.ac.unpas.tiketBioskop.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.tiketBioskop.model.TiketBioskop
import id.ac.unpas.tiketBioskop.ui.theme.Purple700
import id.ac.unpas.tiketBioskop.ui.theme.Teal200


@Composable
fun FormTiketBioskop(onSimpan: (TiketBioskop) -> Unit) {
    val context = LocalContext.current
    val id = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val kursi = remember { mutableStateOf(TextFieldValue("")) }
    val namafilm = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Id") },
            value = id.value,
            onValueChange = {
                id.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "01") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Nama Pemesan") }
        )
        OutlinedTextField(
            label = { Text(text = "Kursi") },
            value = kursi.value,
            onValueChange = {
                kursi.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "001") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama film") },
            value = namafilm.value,
            onValueChange = {
                namafilm.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "Nama Film") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val idValue = id.value.text
                val namaValue = nama.value.text
                val kursiValue = kursi.value.text
                val namafilmValue = namafilm.value.text

                // validasi inputan id dan nama
                if (idValue.isBlank() || namaValue.isBlank()) {
                    Toast.makeText(context, "id dan nama harus diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // validasi inputan kursi dan namafilm
                if (kursiValue.isBlank() && namafilmValue.isBlank()) {
                    Toast.makeText(context, "kursi atau namafilm harus diisi salah satu", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val item = TiketBioskop(idValue, namaValue, kursiValue, namafilmValue)
                onSimpan(item)
                id.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                kursi.value = TextFieldValue("")
                namafilm.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                id.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                kursi.value = TextFieldValue("")
                namafilm.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}