<template>
  <div id="map" ref="mapContainer" style="width: 100%; height: 500px;"></div>
</template>

<script>
import mapboxgl from 'mapbox-gl';

export default {
  data() {
    return {
      map: null,
      bars: [],
    };
  },
  mounted() {
    this.initializeMap();
  },
  methods: {
    async initializeMap() {
      mapboxgl.accessToken = 'your-mapbox-access-token';
      this.map = new mapboxgl.Map({
        container: this.$refs.mapContainer,
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [2.3522, 48.8566], // Paris, France
        zoom: 12,
      });

      this.map.on('load', () => {
        this.loadBars();
      });
    },
    async loadBars() {
      const response = await fetch('http://localhost:8080/api/bars');
      this.bars = await response.json();
      this.bars.forEach((bar) => {
        new mapboxgl.Marker()
            .setLngLat([bar.location.coordinates[0], bar.location.coordinates[1]])
            .setPopup(
                new mapboxgl.Popup({ offset: 25 }).setText(
                    `${bar.name}: Happy Hour ${bar.happyHourStart} - ${bar.happyHourEnd}`
                )
            )
            .addTo(this.map);
      });
    },
  },
};
</script>