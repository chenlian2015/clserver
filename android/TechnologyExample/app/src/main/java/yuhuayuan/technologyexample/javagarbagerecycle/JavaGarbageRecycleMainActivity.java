package yuhuayuan.technologyexample.javagarbagerecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import yuhuayuan.technologyexample.R;

import static yuhuayuan.technologyexample.R.layout;

public class JavaGarbageRecycleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_java_garbage_recycle_main);

        findViewById(R.id.a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDate date = new MyDate();
                date = null;
            }
        });

        findViewById(R.id.b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDate date = new MyDate();
                date = null;
                System.gc();
            }
        });

        findViewById(R.id.c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDate date = new MyDate();
                date = null;
                ReferenceTest.drainMemory();
            }
        });

    }

    /*避免使用弱引用
    * Avoid Soft References for Caching
    In practice, soft references are inefficient for caching. The runtime doesn't have enough information on which references to clear and which to keep. Most fatally, it doesn't know what to do when given the choice between clearing a soft reference and growing the heap.
    The lack of information on the value to your application of each reference limits the usefulness of soft references. References that are cleared too early cause unnecessary work; those that are cleared too late waste memory.
    Most applications should use an android.util.LruCache instead of soft references. LruCache has an effective eviction policy and lets the user tune how much memory is allotted.
    * */
    void softReferenceTest()
    {
        SoftReference ref = new SoftReference(new MyDate());
        System.gc();


        if(ref.get() != null)
        {
            ((MyDate)ref.get()).toString();
        }

    }

    /*
    *
    * Weak reference objects, which do not prevent their referents from being made finalizable, finalized, and then reclaimed. Weak references are most often used to implement canonicalizing mappings.

Suppose that the garbage collector determines at a certain point in time that an object is weakly reachable. At that time it will atomically clear all weak references to that object and all weak references to any other weakly-reachable objects from which that object is reachable through a chain of strong and soft references. At the same time it will declare all of the formerly weakly-reachable objects to be finalizable. At the same time or at some later time it will enqueue those newly-cleared weak references that are registered with reference queues.
    * */
    void weakReferenceTest()
    {
        WeakReference ref = new WeakReference(new MyDate());
        System.gc();
        if (ref.get() != null)
        {
            ((MyDate)ref.get()).toString();
        }
    }

    void asnoReferenceTest()
    {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference ref = new PhantomReference(new MyDate(), queue);
        System.gc();
    }
}


