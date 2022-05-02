package com.rohitjakhar.roommigrationrule.data.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rohitjakhar.roommigrationrule.data.model.UserAmountEntity
import com.rohitjakhar.roommigrationrule.data.model.UserHistoryEntity
import com.rohitjakhar.roommigrationrule.data.model.UserNoteEntitiy
import com.rohitjakhar.roommigrationrule.data.model.UserProfileEntity

@Database(
    entities = [UserProfileEntity::class, UserAmountEntity::class, UserNoteEntitiy::class, UserHistoryEntity::class],
    version = 6,
    autoMigrations = [
        // If Add new table and use Auto Migration
        AutoMigration(from = 3, to = 4),
    ],
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `User Amount` (`user_amount` INTEGER NOT NULL, PRIMARY KEY(`user_amount`))")
            }
        }

        // If Add New Table
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `User Note` (`key` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`key`))")
            }
        }

        // If Add Column in the Table
        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE `User Profile` " +
                        " ADD COLUMN user_mobile INTEGER"
                )
            }
        }

        // If Change in the Table's Column
        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new table
                database.execSQL(
                    "CREATE TABLE `users new` (`user_id` INTEGER NOT NULL, `user_name_last` TEXT NOT NULL, `user_mobile` INTEGER, PRIMARY KEY(`user_id`))"
                )

                // Copy the data
                database.execSQL(
                    "INSERT INTO `users new` (`user_id`, `user_name_last`, `user_mobile`) SELECT `user_id`, `user_name`, `user_mobile` FROM `User Profile`"
                )

                // Remove the old table
                database.execSQL("DROP TABLE `User Profile`")

                // Change the table name to the correct one
                database.execSQL("ALTER TABLE `users new` RENAME TO `User Profile`")
            }
        }

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "testapp.db"
        )
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_4_5, MIGRATION_5_6)
            .build()
    }
}
