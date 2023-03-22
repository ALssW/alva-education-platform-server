package cn.alvasw.edu.business.file;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.localdatetime.LocalDateTimeNumberConverter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
public class Number2DateConverter implements Converter<Timestamp> {

	@Override
	public Class<?> supportJavaTypeKey() {
		return Timestamp.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.NUMBER;
	}

	@Override
	public Timestamp convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		return Timestamp.valueOf(new LocalDateTimeNumberConverter()
				.convertToJavaData(cellData, contentProperty, globalConfiguration));
	}

	@Override
	public WriteCellData<?> convertToExcelData(Timestamp value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		return new WriteCellData<>(value.toString().replace(" 00:00:00.0", ""));
	}
}
