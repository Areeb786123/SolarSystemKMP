package main.planets.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import getPlatform
import main.planets.domain.domain.Planet
import openUrl
import org.koin.compose.koinInject


@Composable
fun PlanetDetailScreen(
    planetId: Int,
    navigateBack: () -> Unit
) {

    val planetsViewModel = koinInject<PlanetsViewModel>()
    val state = planetsViewModel.state

    LaunchedEffect(Unit) {
        planetsViewModel.getPlanetById(planetId)
    }

    state.planet?.let { planet ->
        PlanetDetailScreenContent(
            planet = planet,
            navigateBack = navigateBack
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreenContent(
    modifier: Modifier = Modifier,
    planet: Planet,
    navigateBack: () -> Unit
) {

    val scrollState = rememberScrollState()

    if (getPlatform().isMobile) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
//                .border(width = 1.dp, color = Color.White)
        ) {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = planet.name,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                windowInsets = WindowInsets(0.dp),
//                modifier = modifier.border(width = 1.dp, color = Color.White),
            )

            Box(
                modifier = modifier
                    .padding(top = 40.dp)
                    .size(300.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
//                                        .border(width = 1.dp, color = Color.White),
            ) {
                AsyncImage(
                    modifier = modifier.fillMaxSize(),
                    model = planet.img,
                    contentDescription = planet.imgDescription,
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Description",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = planet.description,
                        fontSize = 16.sp
                    )
                }

                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = "Mass",
                                fontSize = 16.sp,
                                modifier = modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterStart)
                            )
                            Text(
                                text = planet.mass,
                                fontSize = 16.sp,
                                modifier = modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    }

                    OutlinedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = "Volume",
                                fontSize = 16.sp,
                                modifier = modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterStart)
                            )
                            Text(
                                text = planet.volume,
                                fontSize = 16.sp,
                                modifier = modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    }

                    OutlinedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = "Wiki",
                                fontSize = 16.sp,
                                modifier = modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterStart)
                            )
                            IconButton(
                                onClick = {

                                },
                                modifier = modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.CenterEnd)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Link,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                }

            }

        }

    } else {

        BoxWithConstraints {

            println("width: $maxWidth")

            if (maxWidth < 740.dp) {

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = planet.name,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    navigateBack()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = null
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        ),
                        windowInsets = WindowInsets(0.dp),
//                modifier = modifier.border(width = 1.dp, color = Color.White)
                        modifier = modifier
                            .padding(top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp)
                    )

                    Box(
                        modifier = modifier
                            .size(500.dp)
                            .clip(CircleShape)
//                                        .border(width = 1.dp, color = Color.White),
                    ) {
                        AsyncImage(
                            modifier = modifier.fillMaxSize(),
                            model = planet.img,
                            contentDescription = planet.imgDescription,
                            contentScale = ContentScale.Fit
                        )
                    }

                    Column(
                        modifier = modifier
                            .padding(top = 80.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Column(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Description",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = planet.description,
                                fontSize = 16.sp
                            )
                        }

                        Column(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            OutlinedCard(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(70.dp),
                                colors = CardDefaults.outlinedCardColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Box(
                                    modifier = modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = "Mass",
                                        fontSize = 16.sp,
                                        modifier = modifier
                                            .padding(start = 16.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    Text(
                                        text = planet.mass,
                                        fontSize = 16.sp,
                                        modifier = modifier
                                            .padding(end = 16.dp)
                                            .align(Alignment.CenterEnd)
                                    )
                                }
                            }

                            OutlinedCard(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(70.dp),
                                colors = CardDefaults.outlinedCardColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Box(
                                    modifier = modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = "Volume",
                                        fontSize = 16.sp,
                                        modifier = modifier
                                            .padding(start = 16.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    Text(
                                        text = planet.volume,
                                        fontSize = 16.sp,
                                        modifier = modifier
                                            .padding(end = 16.dp)
                                            .align(Alignment.CenterEnd)
                                    )
                                }
                            }

                            OutlinedCard(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(70.dp),
                                colors = CardDefaults.outlinedCardColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Box(
                                    modifier = modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = "Wiki",
                                        fontSize = 16.sp,
                                        modifier = modifier
                                            .padding(start = 16.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    IconButton(
                                        onClick = {
                                            openUrl(planet.wikiLink)
                                        },
                                        modifier = modifier
                                            .padding(end = 16.dp)
                                            .align(Alignment.CenterEnd)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Link,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }

                        }

                    }

                }

            } else {

                Column {

                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = planet.name,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    navigateBack()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = null
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        ),
                        windowInsets = WindowInsets(0.dp),
//                modifier = modifier.border(width = 1.dp, color = Color.White)
                        modifier = modifier
                            .padding(top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp)

                    )

                    Row(
                        modifier = modifier
                            .fillMaxSize()
                    ) {

                        Box(
                            modifier = modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {

                            Box(
                                modifier = modifier
                                    .size(500.dp)
                                    .clip(CircleShape)
//                                        .border(width = 1.dp, color = Color.White),
                            ) {
                                AsyncImage(
                                    modifier = modifier.fillMaxSize(),
                                    model = planet.img,
                                    contentDescription = planet.imgDescription,
                                    contentScale = ContentScale.Fit
                                )
                            }

                        }

                        Box(
                            modifier = modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {

                            Column(
                                modifier = modifier
                                    .padding(top = 80.dp, start = 24.dp, end = 24.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                Column(
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Text(
                                        text = "Description",
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = planet.description,
                                        fontSize = 16.sp
                                    )
                                }

                                Column(
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {

                                    OutlinedCard(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height(70.dp),
                                        colors = CardDefaults.outlinedCardColors(
                                            containerColor = Color.Transparent
                                        )
                                    ) {
                                        Box(
                                            modifier = modifier
                                                .fillMaxSize()
                                        ) {
                                            Text(
                                                text = "Mass",
                                                fontSize = 16.sp,
                                                modifier = modifier
                                                    .padding(start = 16.dp)
                                                    .align(Alignment.CenterStart)
                                            )
                                            Text(
                                                text = planet.mass,
                                                fontSize = 16.sp,
                                                modifier = modifier
                                                    .padding(end = 16.dp)
                                                    .align(Alignment.CenterEnd)
                                            )
                                        }
                                    }

                                    OutlinedCard(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height(70.dp),
                                        colors = CardDefaults.outlinedCardColors(
                                            containerColor = Color.Transparent
                                        )
                                    ) {
                                        Box(
                                            modifier = modifier
                                                .fillMaxSize()
                                        ) {
                                            Text(
                                                text = "Volume",
                                                fontSize = 16.sp,
                                                modifier = modifier
                                                    .padding(start = 16.dp)
                                                    .align(Alignment.CenterStart)
                                            )
                                            Text(
                                                text = planet.volume,
                                                fontSize = 16.sp,
                                                modifier = modifier
                                                    .padding(end = 16.dp)
                                                    .align(Alignment.CenterEnd)
                                            )
                                        }
                                    }

                                    OutlinedCard(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height(70.dp),
                                        colors = CardDefaults.outlinedCardColors(
                                            containerColor = Color.Transparent
                                        )
                                    ) {
                                        Box(
                                            modifier = modifier
                                                .fillMaxSize()
                                        ) {
                                            Text(
                                                text = "Wiki",
                                                fontSize = 16.sp,
                                                modifier = modifier
                                                    .padding(start = 16.dp)
                                                    .align(Alignment.CenterStart)
                                            )
                                            IconButton(
                                                onClick = {
                                                    openUrl(planet.wikiLink)
                                                },
                                                modifier = modifier
                                                    .padding(end = 16.dp)
                                                    .align(Alignment.CenterEnd)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Link,
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

    }

}