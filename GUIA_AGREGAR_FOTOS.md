# 📸 Guía para Agregar Fotos a los Créditos

## 🎯 Estado Actual
✅ **Código preparado** para mostrar fotos de los miembros del equipo
✅ **Placeholders temporales** agregados (siluetas de personas)
✅ **Estructura lista** para 12 personas en total

## 👥 Estructura de Equipos

### 🔧 Equipo de Desarrollo (2 personas)
- `dev1ImageView` - Desarrollador Principal
- `dev2ImageView` - Desarrollador Secundario

### 🏥 Equipo Médico (5 personas)
- `med1ImageView` - Dr. Especialista Renal
- `med2ImageView` - Dra. Nefróloga Clínica
- `med3ImageView` - Dr. Investigador Médico
- `med4ImageView` - Dra. Pediatra Especializada
- `med5ImageView` - Dr. Médico General

### 🌐 Equipo de Traducción (5 personas)
- `trans1ImageView` - Traductor Principal Español
- `trans2ImageView` - Traductor Principal Inglés
- `trans3ImageView` - Revisor de Contenido
- `trans4ImageView` - Traductor Especializado
- `trans5ImageView` - Corrector de Estilo

## 📋 Pasos para Agregar Fotos Reales

### 1️⃣ Agregar las imágenes a la carpeta drawable
- Copia tus fotos a: `app/src/main/res/drawable/`
- Nombra las imágenes con formato: `nombre_persona.jpg` o `nombre_persona.png`
- Ejemplos: `dev1_photo.jpg`, `med1_photo.png`, etc.

### 2️⃣ Modificar el código en CreditsActivity.kt

#### Para el equipo de desarrollo:
```kotlin
// Reemplaza estas líneas:
findViewById<android.widget.ImageView>(R.id.dev1ImageView).setImageResource(R.drawable.person_placeholder)
findViewById<android.widget.ImageView>(R.id.dev2ImageView).setImageResource(R.drawable.person_placeholder)

// Por estas (descomenta y ajusta los nombres):
findViewById<android.widget.ImageView>(R.id.dev1ImageView).setImageResource(R.drawable.dev1_photo)
findViewById<android.widget.ImageView>(R.id.dev2ImageView).setImageResource(R.drawable.dev2_photo)
```

#### Para el equipo médico:
```kotlin
// Descomenta y ajusta estas líneas:
val medicalPhotos = arrayOf(
    R.drawable.med1_photo,  // Reemplaza con tus imágenes
    R.drawable.med2_photo,
    R.drawable.med3_photo,
    R.drawable.med4_photo,
    R.drawable.med5_photo
)

// Y reemplaza esta línea:
findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(R.drawable.person_placeholder)

// Por esta:
findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(medicalPhotos[i])
```

#### Para el equipo de traducción:
```kotlin
// Descomenta y ajusta estas líneas:
val translationPhotos = arrayOf(
    R.drawable.trans1_photo,  // Reemplaza con tus imágenes
    R.drawable.trans2_photo,
    R.drawable.trans3_photo,
    R.drawable.trans4_photo,
    R.drawable.trans5_photo
)

// Y reemplaza esta línea:
findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(R.drawable.person_placeholder)

// Por esta:
findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(translationPhotos[i])
```

### 3️⃣ Recomendaciones para las Fotos

#### 📏 Tamaño y Formato:
- **Dimensiones recomendadas:** 400x400 píxeles (cuadradas)
- **Formatos:** JPG o PNG
- **Tamaño máximo:** 100KB por imagen

#### 🎨 Estilo:
- **Fondo:** Preferiblemente blanco o de color sólido
- **Persona:** Centrada, bien iluminada
- **Calidad:** Nítida y profesional

#### 📁 Nombres sugeridos:
```
dev1_photo.jpg  - Desarrollador Principal
dev2_photo.jpg  - Desarrollador Secundario

med1_photo.jpg  - Dr. Especialista Renal
med2_photo.jpg  - Dra. Nefróloga Clínica
med3_photo.jpg  - Dr. Investigador Médico
med4_photo.jpg  - Dra. Pediatra Especializada
med5_photo.jpg  - Dr. Médico General

trans1_photo.jpg - Traductor Principal Español
trans2_photo.jpg - Traductor Principal Inglés
trans3_photo.jpg - Revisor de Contenido
trans4_photo.jpg - Traductor Especializado
trans5_photo.jpg - Corrector de Estilo
```

## 🔄 Proceso Rápido

1. **Agrega las 12 fotos** a `app/src/main/res/drawable/`
2. **Modifica CreditsActivity.kt** según las instrucciones anteriores
3. **Actualiza los nombres y correos** si es necesario
4. **Compila y prueba** la aplicación

## ✅ Verificación

Después de agregar las fotos, verifica que:
- [ ] Todas las imágenes se muestran correctamente
- [ ] Las imágenes están centradas y con buen tamaño
- [ ] Los nombres y correos son correctos
- [ ] La aplicación no se cierra al abrir los créditos

## 🆘 Si tienes problemas

1. **Error de imagen no encontrada:** Verifica que los nombres de archivo coincidan exactamente
2. **Imagen muy grande:** Redimensiona a 400x400 píxeles
3. **Imagen no se muestra:** Limpia y recompila el proyecto

---

**🎉 Listo! Una vez que agregues las fotos, la pantalla de créditos se verá mucho más profesional y personalizada.**