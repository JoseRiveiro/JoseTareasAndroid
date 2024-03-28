package com.example.myapplication.data.repository

/*
class QueryTask @Inject constructor(private val tareasdb:SQLiteDatabase){
    suspend fun  getDetailsTask(id:Long): String{
        var detalles : String
        withContext(Dispatchers.IO){

            var details: String = ""

            val cursor: Cursor? = tareasdb.rawQuery("SELECT ${TareasDB.COLUMN_details} FROM ${TareasDB.TABLE_NAME} WHERE ${TareasDB.COLUMN_id} = ?", arrayOf(id.toString()))
            cursor?.use {
                if (it.moveToFirst()) {
                    details = it.getString(it.getColumnIndexOrThrow(TareasDB.COLUMN_details))
                }
            }
            detalles = details
            cursor?.close()
            tareasdb.close()



        }
        return detalles

    }
}
/*
  suspend fun  getDetailsTask(id:Long): String{
        var detalles : String
        withContext(Dispatchers.IO){

            var details: String = ""

            val cursor: Cursor? = db.rawQuery("SELECT ${TareasDB.COLUMN_details} FROM ${TareasDB.TABLE_NAME} WHERE ${TareasDB.COLUMN_id} = ?", arrayOf(id.toString()))
            cursor?.use {
                if (it.moveToFirst()) {
                    details = it.getString(it.getColumnIndexOrThrow(TareasDB.COLUMN_details))
                }
            }
            detalles = details
            cursor?.close()
           db.close()



        }
             return detalles

    }
 */