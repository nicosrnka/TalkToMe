import com.example.talktome.models.AddACaregiver;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("AddCaregiver")
    Call<AddACaregiver> addCaregiver(@Body AddACaregiver post);
}
