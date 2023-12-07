package hr.ferit.antonioparadzik.orwma_lv8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(),
    PersonRecyclerAdapter.ContentListener {
    private val db = Firebase.firestore
    private lateinit var recyclerAdapter: PersonRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.personList)
        val saveButton = findViewById<Button>(R.id.button)
        val imageUrl = findViewById<EditText>(R.id.EditImageUrl)
        val personName = findViewById<EditText>(R.id.EditPersonName)
        val personDescription = findViewById<EditText>(R.id.EditPersonDescription)



        db.collection("persons")
            .get()
            .addOnSuccessListener { result ->
                var personList = ArrayList<Person>()
                for (data in result.documents) {
                    val person = data.toObject(Person::class.java)
                    if (person != null) {
                        person.id = data.id
                        personList.add(person)
                    }
                }
                recyclerAdapter = PersonRecyclerAdapter(personList,
                    this@MainActivity)

                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                    adapter = recyclerAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.",
                    exception)
            }
        saveButton.setOnClickListener{
            val person = Person(imageUrl = imageUrl.text.toString(), name = personName.text.toString(), description = personDescription.text.toString())
            db.collection("persons").add(person).addOnSuccessListener {
                person.id=it.id
                recyclerAdapter.addItem(person)
            }

        }

    }
    override fun onItemButtonClick(index: Int, person: Person, clickType:
    ItemClickType) {
        if (clickType == ItemClickType.EDIT) {
            db.collection("persons")
                .document(person.id)
                .set(person)
        }
        else if (clickType == ItemClickType.REMOVE) {
            recyclerAdapter.removeItem(index)
            db.collection("persons")
                .document(person.id)
                .delete()
        }
    }
}