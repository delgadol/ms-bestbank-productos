package com.bestbank.productos.domain.repository;

import com.bestbank.productos.domain.utils.GrupoProducto;
import com.bestbank.productos.domain.utils.TipoProducto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que simula un repositorio de base de datos para fines de emulación o pruebas.
 */
public class DatabaseEmulatorRespo {
  
  private Map<String, Map<String, Object>> database;
  
  private static final String COMISION = "comision";
  private static final String MAX_OPERACIONES_MES = "maxOperacionesMes";
  private static final String COST_EXTRA_OPERACIONES_MES = "costExtraOperacionesMes";
  private static final String MIN_DIA_MES_OPERACION = "minDiaMesOperacion";
  private static final String TIPO_PRODUCTO = "tipoProducto";
  private static final String GRUPO_PRODUCTO = "grupoProducto";
  private static final String MIN_SALDO_MENSUAL = "minSaldoMensual";
  private static final String MAX_PROD_PERSONAL = "maxProdPersonal";
  private static final String COSTO_MIN_SALDO_MENSUAL = "costMinSaldoMensual";
  private static final String MAX_PROD_EMPRESARIAL = "maxProdEmpresarial";
  private static final String REQ_PREVIOS = "reqPrevios";
      
  

  public DatabaseEmulatorRespo() {
    database = new HashMap<>();
    initializeData();
  }

  private void initializeData() {
    
    // CTAA
    Map<String, Object> ctaaData = new HashMap<>();
    ctaaData.put(COMISION, 0.0D);
    ctaaData.put(MAX_OPERACIONES_MES, 4);
    ctaaData.put(COST_EXTRA_OPERACIONES_MES, 16.00D);
    ctaaData.put(MIN_DIA_MES_OPERACION, 0);
    ctaaData.put(TIPO_PRODUCTO, TipoProducto.CTAA);
    ctaaData.put(GRUPO_PRODUCTO, GrupoProducto.PASIVOS);
    ctaaData.put(MIN_SALDO_MENSUAL, 0.00D);
    ctaaData.put(MAX_PROD_PERSONAL, 1);
    ctaaData.put(COSTO_MIN_SALDO_MENSUAL, 0.0D);
    ctaaData.put(MAX_PROD_EMPRESARIAL, 0);
    ctaaData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());      
    database.put("CTAA", ctaaData);
      

    // CTCC
    Map<String, Object> ctccData = new HashMap<>();
    ctccData.put(COMISION, 0.0D);
    ctccData.put(MAX_OPERACIONES_MES, Integer.MAX_VALUE);
    ctccData.put(COST_EXTRA_OPERACIONES_MES, 0.00D);
    ctccData.put(MIN_DIA_MES_OPERACION, 0);
    ctccData.put(TIPO_PRODUCTO, TipoProducto.CTCC);
    ctccData.put(GRUPO_PRODUCTO, GrupoProducto.PASIVOS);
    ctccData.put(MIN_SALDO_MENSUAL, 0.00D);
    ctccData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    ctccData.put(MAX_PROD_PERSONAL, 1);
    ctccData.put(MAX_PROD_EMPRESARIAL, Integer.MAX_VALUE);
    ctccData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());
    database.put("CTCC", ctccData);

    // DPFJ
    Map<String, Object> dpfjData = new HashMap<>();
    dpfjData.put(COMISION, 0.0D);
    dpfjData.put(MAX_OPERACIONES_MES, Integer.MAX_VALUE);
    dpfjData.put(COST_EXTRA_OPERACIONES_MES, 0.00D);
    dpfjData.put(MIN_DIA_MES_OPERACION, 28);
    dpfjData.put(TIPO_PRODUCTO, TipoProducto.DPFJ);
    dpfjData.put(GRUPO_PRODUCTO, GrupoProducto.PASIVOS);
    dpfjData.put(MIN_SALDO_MENSUAL, 0.00D);
    dpfjData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    dpfjData.put(MAX_PROD_PERSONAL, 1);
    dpfjData.put(MAX_PROD_EMPRESARIAL, 0);
    dpfjData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());
    database.put("DPFJ", dpfjData);

    // CRPS
    Map<String, Object> crpsData = new HashMap<>();
    crpsData.put(COMISION, 0.0D);
    crpsData.put(MAX_OPERACIONES_MES, Integer.MAX_VALUE);
    crpsData.put(COST_EXTRA_OPERACIONES_MES, 0.00D);
    crpsData.put(MIN_DIA_MES_OPERACION, 28);
    crpsData.put(TIPO_PRODUCTO, TipoProducto.CRPS);
    crpsData.put(GRUPO_PRODUCTO, GrupoProducto.ACTIVOS);
    crpsData.put(MIN_SALDO_MENSUAL, 0.00D);
    crpsData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    crpsData.put(MAX_PROD_PERSONAL, 0);
    crpsData.put(MAX_PROD_EMPRESARIAL, 0);
    crpsData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());
    database.put("CRPS", crpsData);

    // CREM
    Map<String, Object> crenData = new HashMap<>();
    crenData.put(COMISION, 0.0D);
    crenData.put(MAX_OPERACIONES_MES, Integer.MAX_VALUE);
    crenData.put(COST_EXTRA_OPERACIONES_MES, 0.00D);
    crenData.put(MIN_DIA_MES_OPERACION, 0);
    crenData.put(TIPO_PRODUCTO, TipoProducto.CREM);
    crenData.put(GRUPO_PRODUCTO, GrupoProducto.ACTIVOS);
    crenData.put(MIN_SALDO_MENSUAL, 0.00D);
    crenData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    crenData.put(MAX_PROD_PERSONAL, 0);
    crenData.put(MAX_PROD_EMPRESARIAL, Integer.MAX_VALUE);
    crenData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());
    database.put("CREM", crenData);

    // CRTC
    Map<String, Object> crtcData = new HashMap<>();
    crtcData.put(COMISION, 0.0D);
    crtcData.put(MAX_OPERACIONES_MES, Integer.MAX_VALUE);
    crtcData.put(COST_EXTRA_OPERACIONES_MES, 0.00D);
    crtcData.put(MIN_DIA_MES_OPERACION, 0);
    crtcData.put(TIPO_PRODUCTO, TipoProducto.CRTC);
    crtcData.put(GRUPO_PRODUCTO, GrupoProducto.ACTIVOS);
    crtcData.put(MIN_SALDO_MENSUAL, 0.00D);
    crtcData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    crtcData.put(MAX_PROD_PERSONAL, Integer.MAX_VALUE);
    crtcData.put(MAX_PROD_EMPRESARIAL, Integer.MAX_VALUE);
    crtcData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());
    database.put("CRTC", crtcData);

    // CTPVIP
    Map<String, Object> ctvipData = new HashMap<>();
    ctvipData.put(COMISION, 0.0D);
    ctvipData.put(MAX_OPERACIONES_MES, 20);
    ctvipData.put(COST_EXTRA_OPERACIONES_MES, 16.00D);
    ctvipData.put(MIN_DIA_MES_OPERACION, 0);
    ctvipData.put(TIPO_PRODUCTO, TipoProducto.CRTC);
    ctvipData.put(GRUPO_PRODUCTO, GrupoProducto.PASIVOS);
    ctvipData.put(MIN_SALDO_MENSUAL, 500.0D);
    ctvipData.put(COSTO_MIN_SALDO_MENSUAL, 5.00D);
    ctvipData.put(MAX_PROD_PERSONAL, Integer.MAX_VALUE);
    ctvipData.put(MAX_PROD_EMPRESARIAL, 0);
    List<TipoProducto> ctvipPrevios = Arrays.asList(TipoProducto.CRTC);
    ctvipData.put(REQ_PREVIOS, ctvipPrevios);
    database.put("CTPVIP", ctvipData);
      
    // CTEPIME
    Map<String, Object> ctepymeData = new HashMap<>();
    ctepymeData.put(COMISION, 0.0D);
    ctepymeData.put(MAX_OPERACIONES_MES, 20);
    ctepymeData.put(COST_EXTRA_OPERACIONES_MES, 16.00D);
    ctepymeData.put(MIN_DIA_MES_OPERACION, 0);
    ctepymeData.put(TIPO_PRODUCTO, TipoProducto.CRTC);
    ctepymeData.put(GRUPO_PRODUCTO, GrupoProducto.PASIVOS);
    ctepymeData.put(MIN_SALDO_MENSUAL, 0.00D);
    ctepymeData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    ctepymeData.put(MAX_PROD_PERSONAL, 0);
    ctepymeData.put(MAX_PROD_EMPRESARIAL, Integer.MAX_VALUE);
    List<TipoProducto> ctepymePrevios = Arrays.asList(TipoProducto.CRTC, TipoProducto.CTCC);
    ctepymeData.put(REQ_PREVIOS, ctepymePrevios);
    database.put("CTEPIME", ctvipData);
    
    // CTYANKI
    Map<String, Object> ctyankiData = new HashMap<>();
    ctyankiData.put(COMISION, 0.0D);
    ctyankiData.put(MAX_OPERACIONES_MES, 0);
    ctyankiData.put(COST_EXTRA_OPERACIONES_MES, 0.00D);
    ctyankiData.put(MIN_DIA_MES_OPERACION, 0);
    ctyankiData.put(TIPO_PRODUCTO, TipoProducto.CTYANKI);
    ctyankiData.put(GRUPO_PRODUCTO, GrupoProducto.PASIVOS);
    ctyankiData.put(MIN_SALDO_MENSUAL, 0.00D);
    ctyankiData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    ctyankiData.put(MAX_PROD_PERSONAL, 1);
    ctyankiData.put(MAX_PROD_EMPRESARIAL, 0);
    ctyankiData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());
    database.put("CTYANKI", ctyankiData);
    
    // CTBCSOL
    Map<String, Object> ctbcsolData = new HashMap<>();
    ctbcsolData.put(COMISION, 0.0D);
    ctbcsolData.put(MAX_OPERACIONES_MES, 0);
    ctbcsolData.put(COST_EXTRA_OPERACIONES_MES, 0.00D);
    ctbcsolData.put(MIN_DIA_MES_OPERACION, 0);
    ctbcsolData.put(TIPO_PRODUCTO, TipoProducto.CTBCSOL);
    ctbcsolData.put(GRUPO_PRODUCTO, GrupoProducto.PASIVOS);
    ctbcsolData.put(MIN_SALDO_MENSUAL, 0.00D);
    ctbcsolData.put(COSTO_MIN_SALDO_MENSUAL, 0.00D);
    ctbcsolData.put(MAX_PROD_PERSONAL, 1);
    ctbcsolData.put(MAX_PROD_EMPRESARIAL, 0);
    ctbcsolData.put(REQ_PREVIOS, new ArrayList<TipoProducto>());
    database.put("CTBCSOL", ctbcsolData);
    
  }

  public Map<String, Object> getModelData(String modelName) {
    return database.get(modelName);
  }

  /**
   * Actualiza los datos del modelo identificado por el nombre de modelo y 
   * la clave con el valor proporcionado.
   *
   * @param modelName El nombre del modelo a actualizar.
   * @param key       La clave del dato a actualizar.
   * @param value     El nuevo valor para el dato a actualizar.
   */
  public void updateModelData(String modelName, String key, Object value) {
    Map<String, Object> modelData = database.get(modelName);
    if (modelData != null) {
      modelData.put(key, value);
    }
  }

  /**
   * Elimina el modelo identificado por el nombre de modelo.
   *
   * @param modelName El nombre del modelo a eliminar.
   */
  public void deleteModel(String modelName) {
    database.remove(modelName);
  }

}
