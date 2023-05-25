import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { PersonagemComponent } from './pages/personagem/personagem.component';
import { UsuarioComponent } from './pages/usuario/usuario.component';
import { ClasseComponent } from './pages/classe/classe.component';


const routes: Routes = [
  { path: "", component: LoginComponent},
  { path: "home", component: HomeComponent},
  { path: "personagens", component: PersonagemComponent},
  { path: "usuarios", component: UsuarioComponent},
  { path: "classes", component: ClasseComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
