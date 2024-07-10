package com.example.recipesearch.network

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.recipesearch.models.Recipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

//class RecipesDataStore(context: Context) {
//    private val Context.dataStore: DataStore<Recipes> by dataStore(
//        fileName = "recipes.pb",
//        serializer = RecipesSerializer
//    )
//
//    private val dataStore = context.dataStore
//
//    val recipesFlow: Flow<Result<Recipes?>> = dataStore.data
//        .catch { exception ->
////            if (exception is IOException) {
////                emit(Result.failure(exception))
////            } else {
//                throw exception
////            }
//        }
//        .map { recipes ->
//            Result.success(recipes)
//        }
//
//    suspend fun updateRecipes(recipes: Recipes) {
//        dataStore.updateData { currentRecipes ->
//            recipes
//        }
//    }
//}


//
//object RecipesSerializer : Serializer<Recipes> {
//    override val defaultValue: Recipes = Recipes.getDefaultInstance()
//
//    override suspend fun readFrom(input: InputStream): Recipes {
//        return try {
//            Recipes.parseFrom(input)
//        } catch (exception: InvalidProtocolBufferException) {
//            throw CorruptionException("Cannot read proto.", exception)
//        }
//    }
//
//    override suspend fun writeTo(t: Recipes, output: OutputStream) = withContext(Dispatchers.IO) {
//        t.writeTo(output)
//    }
//}
