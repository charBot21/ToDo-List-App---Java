package com.carlostorres.ibericajava.ui.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.carlostorres.ibericajava.R;
import com.carlostorres.ibericajava.data.local.entity.Notes;
import com.carlostorres.ibericajava.data.local.prefrns.PrefernsProvidr;
import com.carlostorres.ibericajava.ui.adapter.NotsAdapter;
import com.carlostorres.ibericajava.ui.viewmodel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private NotesViewModel viewModel;
    private static final int ADD_REQUEST = 1;
    private static final int EDIT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addBtn();

        RecyclerView recyclerView = findViewById(R.id.rv_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        NotsAdapter adapter = new NotsAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        viewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(HomeActivity.this, getString(R.string.delete_successful), Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NotsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Notes note) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                intent.putExtra(AddActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(AddActivity.EXTRA_TIMESTAMP, note.getTimeStamp());
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

        logout();
    }

    private void logout() {

        FloatingActionButton btn = findViewById(R.id.fbLogout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefernsProvidr.saveUser("", HomeActivity.this);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addBtn() {
        FloatingActionButton btn = findViewById(R.id.fbAddNote);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == ADD_REQUEST && resultCode == RESULT_OK ) {
            String title = data.getStringExtra(AddActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddActivity.EXTRA_DESCRIPTION);
            Long timestamp = data.getLongExtra(AddActivity.EXTRA_TIMESTAMP, 1);

            Notes note = new Notes(title, description, timestamp);
            viewModel.insert(note);
            Toast.makeText(this, getString(R.string.add_successful), Toast.LENGTH_SHORT).show();
        } else if ( requestCode == EDIT_REQUEST && resultCode == RESULT_OK ) {
            int id = data.getIntExtra(AddActivity.EXTRA_ID, -1);

            if ( id == -1 ) {
                Toast.makeText(this, getString(R.string.update_failed), Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddActivity.EXTRA_DESCRIPTION);
            Long timestamp = data.getLongExtra(AddActivity.EXTRA_TIMESTAMP, 1);
            Notes note = new Notes(title, description, timestamp);
            note.setId(id);
            viewModel.update(note);

        }

    }
}