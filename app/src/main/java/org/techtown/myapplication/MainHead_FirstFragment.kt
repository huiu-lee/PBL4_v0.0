package org.techtown.myapplication

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_first_mainhead.*
import org.joda.time.format.DateTimeFormat
import org.techtown.myapplication.databinding.FragmentFirstMainheadBinding
import retrofit2.Call
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainHead_FirstFragment : Fragment(){

    lateinit var items : Array<ModelWeather>

    lateinit var b_t : Button

    private var base_date = "20220311"  // 발표 일자
    private var base_time = "1400"      // 발표 시각

    lateinit var weather_icon : ImageView
    lateinit var location : TextView

    private var curPoint : Point? = null    // 현재 위치의 격자 좌표를 저장할 포인트

    private var _binding : FragmentFirstMainheadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_first_mainhead, container, false)

        var degrees = view.findViewById<TextView>(R.id.degrees)
        var main_dateTv2 = view.findViewById<TextView>(R.id.main_dateTv2)
        weather_icon =view.findViewById(R.id.weather_icon)
        location = view.findViewById(R.id.location)

        // 내 위치 위경도 가져와서 날씨 정보 설정하기
        requestLocation(view)

        val timeDate: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = LocalDateTime.now()
            val dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            val nowString = date.format(dtf)
            timeDate = nowString
        } else {
            val date = org.joda.time.LocalDateTime.now()
            val dtf = DateTimeFormat.forPattern("yyyy.MM.dd")
            val jodaTime = dtf.parseDateTime(date.toString())
            val nowString = DateTimeFormat.forPattern("yyyy.MM.dd").print(jodaTime)
            timeDate = nowString
        }

        // 날짜 텍스트 데이터 셋팅
        main_dateTv2.text = timeDate

        return view
    }

    // 날씨 가져와서 설정하기
    private fun setWeather(nx: Int, ny: Int) {
        // 준비 단계 : base_date(발표 일자), base_time(발표 시각)
        // 현재 날짜, 시간 정보 가져오기
        val cal = Calendar.getInstance()
        base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
        val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 분
        // API 가져오기 적당하게 변환
        base_time = Common().getBaseTime(timeH, timeM)
        // 현재 시각이 00시이고 45분 이하여서 baseTime이 2330이면 어제 정보 받아오기
        if (timeH == "00" && base_time == "2330") {
            cal.add(Calendar.DATE, -1).toString()
            base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
        }

        // 날씨 정보 가져오기
        // (한 페이지 결과 수 = 60, 페이지 번호 = 1, 응답 자료 형식-"JSON", 발표 날싸, 발표 시각, 예보지점 좌표)
        val call = ApiObject.retrofitService.GetWeather(60, 1, "JSON", base_date, base_time, nx, ny)

        // 비동기적으로 실행하기
        call.enqueue(object : retrofit2.Callback<WEATHER> {
            // 응답 성공 시
            override fun onResponse(
                call: retrofit2.Call<WEATHER>,
                response: retrofit2.Response<WEATHER>
            ) {
                if (response.isSuccessful) {
                    var degree = view?.findViewById<TextView>(R.id.degrees)

                    // 날씨 정보 가져오기
                    val it: List<ITEM> = response.body()!!.response.body.items.item

                    // 현재 시각부터 1시간 뒤의 날씨 6개를 담을 배열
                    val weatherArr = arrayOf(ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather())

                    // 배열 채우기
                    var index = 0
                    val totalCount = response.body()!!.response.body.totalCount - 1
                    for (i in 0..totalCount) {
                        index %= 6
                        when(it[i].category) {
                            "PTY" -> weatherArr[index].rainType = it[i].fcstValue     // 강수 형태
                            "REH" -> weatherArr[index].humidity = it[i].fcstValue     // 습도
                            "SKY" -> weatherArr[index].sky = it[i].fcstValue          // 하늘 상태
                            "T1H" -> weatherArr[index].temp = it[i].fcstValue         // 기온
                            else -> continue
                        }
                        index++
                    }

                    // 각 날짜 배열 시간 설정
                    for (i in 0..5) weatherArr[i].fcstTime = it[i].fcstTime

                    if (degree != null) {
                        degree.text = weatherArr[0].temp
                    }

                    getRainType(weatherArr[0].rainType, weatherArr[0].sky)

                    Log.d("api tem", weatherArr[0].temp)
                }
            }

            // 응답 실패 시
            override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                val tvError = binding.root.findViewById<TextView>(R.id.tvError)
                tvError.text = "api fail : " +  t.message.toString() + "\n 다시 시도해주세요."
                tvError.visibility = View.VISIBLE
                Log.d("api fail", t.message.toString())
            }
        })
    }


    // 내 현재 위치의 위경도를 격자 좌표로 변환하여 해당 위치의 날씨정보 설정하기
    private fun requestLocation(view: View) {
        val locationClient = LocationServices.getFusedLocationProviderClient(view.context)

        try {
            // 나의 현재 위치 요청
            val locationRequest = LocationRequest.create()
            locationRequest.run {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 60 * 1000    // 요청 간격(1초)
            }
            val locationCallback = object : LocationCallback() {
                // 요청 결과
                override fun onLocationResult(p0: LocationResult?) {
                    p0?.let {
                        for (location in it.locations) {
                            Log.d("api altitute", location.altitude.toString())
                            Log.d("api longtitude", location.longitude.toString())

                            // 현재 위치의 위경도를 격자 좌표로 변환
                            curPoint = Common().dfs_xy_conv(location.latitude, location.longitude)

                            Log.d("api fail2", curPoint.toString())

                            //location 지정해주기
                            getLocation(curPoint!!.x , curPoint!!.y)

                            // nx, ny지점의 날씨 가져와서 설정하기
                            setWeather(curPoint!!.x, curPoint!!.y)

                        }
                    }
                }
            }

            // 내 위치 실시간으로 감지
            locationClient?.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        } catch (e : SecurityException) {
            e.printStackTrace()
        }
    }

    // 하늘 형태
    fun getRainType(rainType : String, skyType : String) {

        when (rainType) {
            "0" -> {
                when (skyType) {
                    "1" -> weather_icon.setImageResource(R.drawable.sun)  //맑음
                    "3" -> weather_icon.setImageResource(R.drawable.cloud_little)  //조금 흐림
                    "4" -> weather_icon.setImageResource(R.drawable.cloud_many)  //흐림
                    else -> "오류 rainType : " + skyType
                }
            }
            "1" -> weather_icon.setImageResource(R.drawable.rain) //비
            "2" -> weather_icon.setImageResource(R.drawable.rainandsnow)  //비 or 눈
            "3" -> weather_icon.setImageResource(R.drawable.snow)  //눈
            else -> "오류 rainType : "+rainType
        }
    }

    // 하늘 형태
    fun getLocation(x : Int, y : Int) {

        if ( x in 48..60 && y in 32..48){
            location.text = "제주"
        }
        else if (x in 70..101 && y in 118..147){
            location.text = "강원"
        }
        else if (x in 63..67 && y in 103..108){
            location.text = "세종"
        }
        else if (x in 98..104 && y in 83..86){
            location.text = "울산"
        }
        else if (x in 66..69 && y in 99..103){
            location.text = "대전"
        }
        else if (x in 57..60 && y in 73..76){
            location.text = "광주"
        }
        else if (x in 21..56 && y in 123..129){
            location.text = "인천"
        }
        else if (x in 86..91 && y in 86..92){
            location.text = "대구"
        }
        else if (x in 97..101 && y in 73..79){
            location.text = "부산"
        }
        else if (x in 53..73 && y in 114..140){
            if (x in 57..63 && y in 124..129){
                location.text = "서울"
            }
            else{
                location.text = "경기"
            }
        }
        else{
            location.text = "  "
        }
    }
}