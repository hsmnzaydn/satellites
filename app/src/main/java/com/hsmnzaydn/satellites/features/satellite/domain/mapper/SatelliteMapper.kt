import com.hsmnzaydn.satellites.features.satellite.data.entities.SatelliteDetailResponse
import com.hsmnzaydn.satellites.features.satellite.data.entities.SatelliteResponse
import com.hsmnzaydn.satellites.features.satellite.domain.entities.Satellite
import com.hsmnzaydn.satellites.features.satellite.domain.entities.SatelliteListItem

fun SatelliteResponse.toSatelliteLisItem() = SatelliteListItem(
    id,name,active
)

fun SatelliteDetailResponse.toSatellite() = Satellite(
    costPerLaunch.toString(), firstFlight, height.toString(), id, mass.toString()
)