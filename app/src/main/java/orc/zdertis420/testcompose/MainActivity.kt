package orc.zdertis420.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orc.zdertis420.testcompose.ui.theme.TestComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestComposeTheme {
                ContactDetails(
                    Contact(
                        "Иван",
                        "Иванович",
                        "Иванов",
                        R.drawable.immortal,
                        true,
                        "8-228-420-42-69",
                        "г. Усть, ул. Пушкина, д. Колотушкина",
                        "cock@nigge.rs"
                    )
                )

            }
        }
    }
}

@Composable
fun RoundInitials(initials: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle_shape),
            contentDescription = null,
        )
        Text(
            initials,
            fontSize = 22.sp
        )
    }
}

@Composable
fun ShowImageOrInitials(imageRes: Int?, initials: String) {
    if (imageRes != null) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Круглое изображение",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
    } else {
        RoundInitials(initials)
    }
}

//@Preview
//@Composable
//fun ShowImageOrInitialsPreview() {
//    ShowImageOrInitials(R.drawable.immortal, "ABOBA")
//}

@Composable
fun InfoRow(phone: String, address: String, email: String?, modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        Column {
            Text("${stringResource(R.string.phone)}: ")
            Text("${stringResource(R.string.address)}: ")
            if (email != null) {
                Text("${stringResource(R.string.email)}: ")
            }
        }

        Column {
            Text(phone)
            Text(address)
            if (email != null) {
                Text(email)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun InfoRowPreview() {
//    InfoRow(
//        "8-999-999-99-99",
//        "г. Усть, ул. Пушкина, д. Колотушкина",
//        "cock@nigge.rs",
//        modifier = Modifier
//            .padding(16.dp)
//    )
//}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val initials = contact.name.take(1) + contact.familyName.take(1)
        ShowImageOrInitials(contact.imageRes, initials)

        Text(
            "${contact.name} ${contact.surname}",
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row {
            Text(
                contact.familyName,
                fontSize = 22.sp,
            )

            if (contact.isFavorite) {
                Image(
                    painterResource(R.drawable.star_shape),
                    contentDescription = "Is fav",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        InfoRow(
            contact.phone,
            contact.address,
            contact.email,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactDetailsPreview() {
    ContactDetails(
        Contact(
            "Иван",
            "Иванович",
            "Иванов",
            R.drawable.immortal,
            true,
            "8-228-420-42-69",
            "г. Усть, ул. Пушкина, д. Колотушкина",
            "cock@nigge.rs"
        )
    )
}

