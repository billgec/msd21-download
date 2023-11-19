import android.app.IntentService
import android.content.Intent
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class NewService : IntentService("NewService") {

    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra("url")
        val fileName = intent?.getStringExtra("fileName")

        try {
            val imageUrl = URL(url)
            val connection = imageUrl.openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            val file = File(getExternalFilesDir(null), fileName)
            FileOutputStream(file).use { output ->
                inputStream.copyTo(output)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
