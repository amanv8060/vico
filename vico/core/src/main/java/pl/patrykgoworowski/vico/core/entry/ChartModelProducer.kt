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

package pl.patrykgoworowski.vico.core.entry

/**
 * A declaration of a [Model] producer that may deliver generated [Model]s asynchronously.
 * It supports animating the difference between the previous and current lists of entries.
 * The [Model] is used by the chart to render the data.
 *
 * @see ChartEntryModel
 */
public interface ChartModelProducer<Model : ChartEntryModel> {

    /**
     * Returns the [ChartEntryModel] for this [ChartModelProducer] synchronously.
     */
    public fun getModel(): Model

    /**
     * Calculates an intermediate list of entries for difference animations for the associated [key], where [progress]
     * is the balance between the previous and current lists of entries.
     */
    public fun progressModel(key: Any, progress: Float)

    /**
     * Registers an update listener associated with a [key].
     *
     * @param key associates a receiver with its listeners. It’s later used to perform difference animations with
     * [progressModel].
     * @param updateListener is called immediately in this function, and when the [ChartModelProducer] receives a new
     * list of entries. The [registerForUpdates] function caller may start an animator, which will order the
     * [ChartModelProducer] to handle the difference animation with [progressModel].
     * @param onModel called when the [ChartModelProducer] has generated the [Model].
     */
    public fun registerForUpdates(key: Any, updateListener: () -> Model?, onModel: (Model) -> Unit)

    /**
     * Unregisters the update listener associated with the given [key].
     */
    public fun unregisterFromUpdates(key: Any)
}
