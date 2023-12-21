<template>
  <div class="pos-f-t">
    <div class="collapse" id="navbarToggleExternalContent">
      <div class="bg-dark p-4">
        <h4 class="text-white">Collapsed content</h4>
        <a href="/" class="text-muted navs-link">Classrooms</a>
        <a href="/history" class="text-muted navs-link">History</a>
      </div>
    </div>
    <nav class="navbar navbar-dark bg-dark">
      <button class="navbar-toggler ms-4" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </nav>
  </div>
</template>

<script>
export default {
  name: "Navbar"
}
</script>

<style scoped>
.navs-link{
  display: flex;
  font-weight: bold;
  font-size: 22px;
  text-decoration: none;
  flex-direction: column;
  transition: .3s ease;
}
.navs-link:hover{
  color: white !important;
}
.navs-link:not(:last-child){
  margin-bottom: 12px;
}
</style>