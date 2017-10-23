package tn.esprit.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.entities.JobOffer;
import tn.esprit.entities.Refugee;

public class GenerateCoverLetterPdf {

	public boolean topdf(JobOffer jo, Refugee r) {

		/* company info(collected from job offer entity) */
		final String cName = jo.getCompanyName();
		final String cAdress = jo.getCompanyAdress();
		final String cNumber = Integer.toString(jo.getContactnumber());
		final String cEmail = jo.getContactEmail();
		
		/* job offer info */		
		final String joTitle = jo.getTitle();
		final String joCname = jo.getContactName();
		final String joField = jo.getFieldOfWork();
		
		/* refugee info */
		final String rName = r.getFirstname() +" "+ r.getLastName();		
		final String rEmail = r.getEmail();
		final String rAdress = r.getAdress();
		final String rNumber = Integer.toString(r.getPhoneNumber());
		
		
		Document document = new Document();
		try {
			// %USERPROFILE%/Desktop/
			String path = System.getProperty("user.home") + "\\Desktop/";
			Random random = new Random();
			PdfWriter.getInstance(document,
					new FileOutputStream(path + "Letter" + random.nextInt(65000 - 1 + 1) + 1 + ".pdf"));

			document.open();

			Font font = new Font(FontFactory.getFont("Times New Roman"));
			font.setStyle(Font.BOLD);

			Paragraph info1 = new Paragraph(cName, font);
			Paragraph info2 = new Paragraph(cAdress, font);
			Paragraph info3 = new Paragraph(cNumber, font);
			Paragraph info4 = new Paragraph(cEmail, font);

			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
			Date d = new Date();
			String formattedDate = DATE_FORMAT.format(d);

			Paragraph info10 = new Paragraph(formattedDate, font);

			document.add(info1);
			document.add(info2);
			document.add(info3);
			document.add(info4);
			document.add(info10);
			document.add(Chunk.NEWLINE);

			Chunk c1 = new Chunk("Ref : " + joTitle, font);
			document.add(c1);
			document.add(Chunk.NEWLINE);

			document.add(new Paragraph("Dear " + joCname));
			document.add(Chunk.NEWLINE);

			document.add(new Paragraph("I am writing this letter to apply for the " + joTitle
					+ " position at "+cName+". " + "I saw an open call for the applications on your website"));
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("I am very familiar with your work , since I have been following it for a long time. "
					+ "Moreover, I was very pleased to find out about this employment possibility, since I perceive your Organization "
					+ "as one of the leading companies/organizations in " + joField + "."));
			document.add(Chunk.NEWLINE);
			document.add(
					new Paragraph("I believe that my strong educational background and extensive experience in the "
							+ joField + ", make an appropriate candidate "
							+ "for the advertised position. As you can see in the enclosed resume, my responsibilities and duties were quite similar to those required "
							+ "in the ad of your organization, I strongly believe I can perform well all the delegated duties and tasks."));
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph(
					"I perceive this employment opportunity as a significant advancement in my career, " + "since "
							+ cName + " is the leading company in the field. I believe that joining its team can be a good environment"
							+ " to show off my full potential and utilize my skills and knowledge. In that line I am looking forward to your call for an interview. "));
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph(
					"As requested I am enclosing my resume, where you can find details information on my experience and skills. "
							+ "If you need additional information or documents, feel free to contact me via email/phone"));
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Yours faithfully"));
			document.add(Chunk.NEWLINE);

			Paragraph info5 = new Paragraph(rName, font);
			Paragraph info7 = new Paragraph(rAdress, font);
			Paragraph info8 = new Paragraph(rNumber, font);
			Paragraph info9 = new Paragraph(rEmail, font);
			document.add(info5);

			document.add(info7);
			document.add(info8);
			document.add(info9);

			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

}
