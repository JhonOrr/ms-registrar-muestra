package com.labsoluciones.laboratoriolubricante.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class UsuarioEntity implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_usuario")
        private Long idUsuario;
        @Column(name = "dni", nullable = false, length = 8)
        private String dni;
        @Column(name = "nombres", nullable = false, length = 25)
        private String nombres;
        @Column(name = "ape_pat", nullable = false, length = 25)
        private String apellidoPaterno;
        @Column(name = "ape_mat", nullable = false, length = 25)
        private String apellidoMaterno;
        @Column(name = "estado", nullable = false)
        private Integer estado;
        @Column(name = "usua_create", length = 45)
        private String usuaCrea;
        @Column(name = "date_create")
        private Timestamp dateCreate;
        @Column(name = "usua_modif", length = 45)
        private String usuaModif;
        @Column(name = "date_modif")
        private Timestamp dateModif;
        @Column(name = "usua_delete", length = 45)
        private String usuaDelet;
        @Column(name = "date_delete")
        private Timestamp dateDelet;
        @Column(name="password")
        private String password;
        @Column(name="role")
        private Role role;
        @Column(name="email")
        private String email;
        // Relacion con cliente
        @ManyToOne(optional = false)
        @JoinColumn(name = "id_cliente")
        private ClienteEntity cliente;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }

        @Override
        public String getUsername() {
            return this.email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

}
