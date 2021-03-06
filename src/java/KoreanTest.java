import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.airgroup.model.curiosity.CuriosityFare;
import com.airgroup.model.curiosity.CuriositySearch;
import com.airgroup.model.curiosity.CuriositySegment;
import com.airgroup.services.impl.koreanair.KoreanFlight;

public class KoreanTest {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat
		.forPattern("yyyyMMdd");

	public static void main(String args[]) throws InterruptedException, ExecutionException,
			IOException {

		KoreanFlight flight = new KoreanFlight();
		CuriositySearch search = new CuriositySearch();
		search.setDepartureCode("SGN");
		search.setArrivalCode("LHR");
		search.setOutboundDate(DATE_TIME_FORMATTER.parseDateTime("20130918"));
		// search.setInboundDate(DATE_TIME_FORMATTER.parseDateTime("20130923"));
		search.setAdultsCount(1);
		search.setChildrenCount(0);
		search.setInfantsCount(0);

		List<CuriosityFare> fares = flight.getFare(search);

		for (CuriosityFare fare : fares) {
			// if (fare.getOutboundSegments() != null) {
			System.err.println("OUTBOUND");
			for (CuriositySegment segment : fare.getOutboundSegments()) {
				System.err.println(segment.getCarrier() +
									" " +
									segment.getFlightNumber() +
									" " +
									segment.getDepartureName() +
									" " +
									segment.getArrivalName() +
									" " +
									segment.getDepartureTime() +
									" - " +
									segment.getArrivalTime());
			}
			// } else if (fare.getInboundSegments() != null) {
			System.err.println("INBOUND");
			if (search.isRoundtrip()) {
				for (CuriositySegment segment : fare.getInboundSegments()) {
					System.err.println(segment.getCarrier() +
										" " +
										segment.getFlightNumber() +
										" " +
										segment.getDepartureName() +
										" " +
										segment.getArrivalName() +
										" " +
										segment.getDepartureTime() +
										" - " +
										segment.getArrivalTime());
					// }
				}
			}
		}
	}
}
