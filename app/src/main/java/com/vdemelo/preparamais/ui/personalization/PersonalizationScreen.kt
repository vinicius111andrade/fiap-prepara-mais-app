package com.vdemelo.preparamais.ui.personalization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vdemelo.preparamais.ui.home.OrangeGradientEnd
import com.vdemelo.preparamais.ui.home.OrangeGradientStart

val OrangeButton = Color(0xFFFFA726)
val TextFieldBackground = Color(0xFFE8F5FE)
val SwitchTrackColor = Color(0xFFFFCC80)

@Composable
fun PersonalizationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onBackClick: () -> Unit = { navController.navigateUp() },
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        PersonalizationHeader(onBackClick = onBackClick)
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            PersonalizationScreenContent()
        }
    }
}

@Composable
fun PersonalizationHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(OrangeGradientStart, OrangeGradientEnd),
                        startY = 0f,
                        endY = 300f
                    )
                )
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.offset(x = (-12).dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 8.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "Preparação",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 26.sp
                    )
                    Text(
                        text = "Personalizada",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 26.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PersonalizationScreenContent(
    modifier: Modifier = Modifier,
    onGenerateRecommendations: () -> Unit = {}
) {
    var hasAnimals by remember { mutableStateOf(true) }
    var hasMobilityIssues by remember { mutableStateOf(false) }
    var apartmentType by remember { mutableStateOf("Apartamento") }
    var numberOfPeople by remember { mutableStateOf("4+") }
    var birthDate by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Question 1: Animals
        QuestionToggle(
            question = "Você tem animais de estimação em casa?",
            isChecked = hasAnimals,
            onCheckedChange = { hasAnimals = it }
        )

        // Question 2: Mobility Issues
        QuestionToggle(
            question = "Você possui mobilidade reduzida ou deficiência?",
            isChecked = hasMobilityIssues,
            onCheckedChange = { hasMobilityIssues = it }
        )

        // Question 3: Where You Live
        QuestionSection(
            question = "Onde Você Mora Atualmente?"
        ) {
            DropdownField(
                value = apartmentType,
                onValueChange = { apartmentType = it },
                options = listOf("Apartamento", "Casa", "Condomínio", "Outro")
            )
        }

        // Question 4: Number of People
        QuestionSection(
            question = "Quantas Pessoas Moram Com Você?"
        ) {
            DropdownField(
                value = numberOfPeople,
                onValueChange = { numberOfPeople = it },
                options = listOf("1", "2", "3", "4+")
            )
        }

        // Question 5: Birth Date
        QuestionSection(
            question = "Data De Nascimento"
        ) {
            DateInputField(
                value = birthDate,
                onValueChange = { birthDate = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Generate Recommendations Button
        Button(
            onClick = onGenerateRecommendations,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OrangeButton
            )
        ) {
            Text(
                text = "Gerar recomendações",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

@Composable
fun QuestionToggle(
    question: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = question,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = SwitchTrackColor,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun QuestionSection(
    question: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = question,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    value: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(TextFieldBackground)
                .menuAnchor()
                .clickable { expanded = true }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown",
                    tint = Color.Gray
                )
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontSize = 16.sp
                        )
                    },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DateInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(TextFieldBackground)
    ) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                // Format as DD/MM/YYYY
                val digitsOnly = newValue.filter { it.isDigit() }
                val formatted = when {
                    digitsOnly.length <= 2 -> digitsOnly
                    digitsOnly.length <= 4 -> "${digitsOnly.take(2)}/${digitsOnly.drop(2)}"
                    digitsOnly.length <= 8 -> "${digitsOnly.take(2)}/${digitsOnly.drop(2).take(2)}/${digitsOnly.drop(4)}"
                    else -> value // Don't update if more than 8 digits
                }
                onValueChange(formatted)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "DD / MM / YYYY",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun PersonalizationScreenPreview() {
    PersonalizationScreen()
}
