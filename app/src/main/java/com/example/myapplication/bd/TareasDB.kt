package com.example.myapplication.bd

import android.provider.BaseColumns

object TareasDB : BaseColumns {
    const val  TABLE_NAME = "TAREASTabla"
    const val   COLUMN_task = "task"
    const val  COLUMN_isCompleted = "Estado"
    const val  COLUMN_id = "id"
    const val COLUMN_details="details"
}

const val SQL_CREATE_ENTRIES = """
    CREATE TABLE ${TareasDB.TABLE_NAME} (
        ${TareasDB.COLUMN_task} TEXT,
        ${TareasDB.COLUMN_isCompleted} TEXT,
        ${TareasDB.COLUMN_id} TEXT PRIMARY KEY,
          ${TareasDB.COLUMN_details} TEXT,
        
    )
    """
private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TareasDB.TABLE_NAME}"