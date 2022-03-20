/*
 * Copyright (c) 2021. Patryk Goworowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.patrykgoworowski.vico.core.marker

import android.graphics.RectF
import pl.patrykgoworowski.vico.core.chart.insets.ChartInsetter
import pl.patrykgoworowski.vico.core.context.DrawContext
import pl.patrykgoworowski.vico.core.entry.ChartEntry
import pl.patrykgoworowski.vico.core.model.Point

/**
 * Highlights points on a chart and displays their corresponding values in a bubble.
 */
public interface Marker : ChartInsetter {

    /**
     * Draws the marker.
     * @param context the [DrawContext] used to draw the marker.
     * @param bounds the bounds in which the marker is drawn.
     * @param markedEntries a list of [EntryModel]s representing the entries to which the marker refers.
     */
    public fun draw(
        context: DrawContext,
        bounds: RectF,
        markedEntries: List<EntryModel>,
    )

    public class EntryModel(
        public val location: Point,
        public val entry: ChartEntry,
        public val color: Int,
    )
}
