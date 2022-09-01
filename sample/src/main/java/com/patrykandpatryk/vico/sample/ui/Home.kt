/*
 * Copyright 2022 Patryk Goworowski and Patryk Michalik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.patrykandpatryk.vico.sample.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatryk.vico.sample.extension.remember
import com.patrykandpatryk.vico.sample.util.getSampleCharts
import com.patrykandpatryk.vico.sample.viewmodel.ShowcaseViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
internal fun Home() {
    val composeShowcaseState = rememberSwipeableState(initialValue = 0)
    val showcaseViewModel = viewModel<ShowcaseViewModel>()
    val sampleCharts = getSampleCharts(
        chartEntryModelProducer = showcaseViewModel.chartEntryModelProducer,
        chartStepEntryModelProducer = showcaseViewModel.chartStepEntryModelProducer,
        composedChartEntryModelProducer = showcaseViewModel.composedChartEntryModelProducer,
        multiChartEntryModelProducer = showcaseViewModel.multiChartEntryModelProducer,
    ).remember()
    Scaffold { paddingValues ->
        Column {
            ChartShowcase(
                sampleCharts = sampleCharts,
                state = composeShowcaseState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
                    .statusBarsPadding(),
            )
        }
    }
}
