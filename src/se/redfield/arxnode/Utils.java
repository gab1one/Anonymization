package se.redfield.arxnode;

import org.deidentifier.arx.ARXConfiguration;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.NodeLogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils {

	private static final NodeLogger logger = NodeLogger.getLogger(Utils.class);

	public static DataCell createCell(DataColumnSpec spec, String value) {
		String cellClass = spec.getType().getCellClass().getSimpleName();
		logger.warn("cell for type: " + cellClass);
		switch (cellClass) {
		case "StringCell":
			return new StringCell(value);
		case "IntCell":
			return new IntCell(Integer.valueOf(value));
		}
		return null;
	}

	private static long time = 0;

	public static void time() {
		time = System.currentTimeMillis();
	}

	public static void time(String task) {
		long duration = System.currentTimeMillis() - time;
		logger.debug(task + " " + duration + "ms");
		time();
	}

	public static String toString(ARXConfiguration cfg) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(cfg);
	}
}
