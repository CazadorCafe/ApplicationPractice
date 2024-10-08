package org.jdc.template.model.db.main

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import org.dbtools.android.room.CloseableDatabaseWrapper
import org.dbtools.android.room.android.AndroidSQLiteOpenHelperFactory
import org.jdc.template.model.db.main.migration.MainMigration2
import org.jdc.template.model.db.main.migration.MainMigration3
import org.jdc.template.model.db.main.migration.MainMigration4
import org.jdc.template.model.db.main.migration.MainMigration5
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MainDatabaseWrapper
@Inject constructor(
    @ApplicationContext context: Context
) : CloseableDatabaseWrapper<MainDatabase>(context) {

    override fun createDatabase(): MainDatabase {
        val openHelperFactory = AndroidSQLiteOpenHelperFactory()

        return Room.databaseBuilder(context, MainDatabase::class.java, MainDatabase.DATABASE_NAME)
            .openHelperFactory(openHelperFactory)
            .addMigrations(
                MainMigration2(),
                MainMigration3(),
                MainMigration4(),
                MainMigration5()
            )
            // Debug -- Show SQL statements
            // .setLoggingQueryCallback(MainDatabase.DATABASE_NAME)
            .build()
    }
}