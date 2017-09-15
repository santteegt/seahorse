/**
 * Copyright 2015, deepsense.io
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

package io.deepsense.deeplang.doperables.spark.wrappers.models

import org.apache.spark.ml.feature.{Word2Vec => SparkWord2Vec, Word2VecModel => SparkWord2VecModel}

import io.deepsense.deeplang.ExecutionContext
import io.deepsense.deeplang.doperables.SparkSingleColumnModelWrapper
import io.deepsense.deeplang.doperables.serialization.SerializableSparkModel
import io.deepsense.deeplang.doperables.spark.wrappers.params.Word2VecParams
import io.deepsense.deeplang.params.Param

class Word2VecModel
  extends SparkSingleColumnModelWrapper[SparkWord2VecModel, SparkWord2Vec]
  with Word2VecParams {

  override protected def getSpecificParams: Array[Param[_]] = Array(
    maxIterations,
    stepSize,
    seed,
    vectorSize,
    numPartitions,
    minCount)

  override protected def loadModel(
      ctx: ExecutionContext,
      path: String): SerializableSparkModel[SparkWord2VecModel] = {
    new SerializableSparkModel(SparkWord2VecModel.load(path))
  }
}
