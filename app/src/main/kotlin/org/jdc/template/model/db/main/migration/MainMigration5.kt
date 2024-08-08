package org.jdc.template.model.db.main.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.dbtools.android.room.ext.createAllViews
import org.dbtools.android.room.ext.dropAllViews
import org.jdc.template.model.db.main.MainDatabase

class MainMigration5: Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // BOTH views and tables are changed

        // drop views
        database.dropAllViews()

        // do other database migrations here

        // recreate views
        database.createAllViews(MainDatabase.DATABASE_VIEW_QUERIES)
    }
}