package com.example.basictabkt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.content.Intent


class Tab2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tab2, container, false)
        // 1. 다량의 데이터
        // 2. Adapter
        // 3. AdapterView - GridView
        val img = intArrayOf(
            R.drawable.c,
            R.drawable.e,
            R.drawable.j,
            R.drawable.q,
            R.drawable.c,
            R.drawable.e,
            R.drawable.j,
            R.drawable.q,
            R.drawable.c,
            R.drawable.e,
            R.drawable.j,
            R.drawable.q,
            R.drawable.c,
            R.drawable.e,
            R.drawable.j,
            R.drawable.q,
            R.drawable.c,
            R.drawable.e,
            R.drawable.j,
            R.drawable.q,
            R.drawable.c
        ) // drawable 폴더에서 가져온 이미지

        // 커스텀 아답타 생성
        val adapter = MyAdapter(
            context!!.applicationContext,
            R.layout.row,   // GridView 항목의 레이아웃 row.xml
            img             // 데이터
        )

        val gv = view.findViewById<GridView>(R.id.gridView1)
        gv.adapter = adapter  // 커스텀 아답타를 GridView 에 적용

        // JAVA8 에 등장한 lambda expression 으로 구현했습니다. 코드가 많이 간결해지네요
        gv.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                // FullScreen
                // Sending image id to FullScreenActivity
                val i = Intent(context!!.applicationContext, FullImageActivity::class.java)
                // passing array index
                i.putExtra("id", position)
                startActivity(i)
            }
        return view
    } // end of onCreate
} // end of class

internal class MyAdapter(var context: Context, var layout: Int, var img: IntArray) : BaseAdapter() {
    var inf: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return img.size
    }

    override fun getItem(position: Int): Any {
        return img[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null)
            convertView = inf.inflate(layout, null)
        val iv = convertView!!.findViewById(R.id.imageView1) as ImageView
        iv.setImageResource(img[position])
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        return convertView
    }
}