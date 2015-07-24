package br.com.hoteldasraposas.acao.reserva;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.hoteldasraposas.acao.Acao;
import br.com.hoteldasraposas.dao.RoomDAO;
import br.com.hoteldasraposas.model.Room;
import br.com.hoteldasraposas.model.RoomType;

public class Disponibilidade implements Acao{
	
	public class DisponibilidadeTipo implements Comparable<DisponibilidadeTipo>  {
		private RoomType tipo;
		private List<Room> quartos;
		public RoomType getTipo() {
			return tipo;
		}
		public void setTipo(RoomType tipo) {
			this.tipo = tipo;
		}
		public List<Room> getQuartos() {
			return quartos;
		}
		public void setQuartos(List<Room> quartos) {
			this.quartos = quartos;
		} 
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof DisponibilidadeTipo) {
				DisponibilidadeTipo outro = (DisponibilidadeTipo)obj;
				return outro.getTipo().getId() == getTipo().getId();
			}
			else if (obj instanceof RoomType) {
				RoomType tipo = (RoomType)obj;
				return tipo.getId() == getTipo().getId();
			}
			else return false;
		}
		
		@Override
		public int compareTo(DisponibilidadeTipo o) {
			return (int)(o.getTipo().getDaylePrice() - getTipo().getDaylePrice());
		}
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date inicio = null, fim = null;
		try {
			inicio = format.parse(req.getParameter("entrada"));
			fim = format.parse(req.getParameter("saida"));

			if (inicio.compareTo(fim) > 0) {
				req.setAttribute("msgErro", 3);
				inicio = fim = null;
			}
		}
		catch (Exception e) {
			req.setAttribute("msgErro", 2);
			inicio = fim = null;
		}
		
		// Limpa variáveis de sessão usadas por outras reservas
		HttpSession session = req.getSession();
		session.removeAttribute("reservaTipoQuarto");
		session.removeAttribute("reservaConfirmacao");
		session.removeAttribute("reserva");
		
		if (inicio != null && fim != null) {
			RoomDAO quartoDao = new RoomDAO();
			
			List<Room> disponiveis = quartoDao.getAvailableList(inicio, fim);
			List<DisponibilidadeTipo> disponibilidadePorTipo = new ArrayList<DisponibilidadeTipo>();
			
			for (Room quarto : disponiveis) {
				DisponibilidadeTipo tipo = null;
				Iterator<DisponibilidadeTipo> dt = disponibilidadePorTipo.iterator();
				while (dt.hasNext()) {
					DisponibilidadeTipo outro = dt.next();
					if (outro.equals(quarto.getType())) {
						tipo = outro;
						break;
					}
				}
				
				if (tipo == null) {
					tipo = new DisponibilidadeTipo();
					tipo.setTipo(quarto.getType());
					tipo.setQuartos(new ArrayList<Room>());
					disponibilidadePorTipo.add(tipo);
				}
				
				tipo.getQuartos().add(quarto);
			}
			
			disponibilidadePorTipo.sort(null);
			
			req.setAttribute("sucesso", true);
			req.setAttribute("lista", disponibilidadePorTipo);
			if (disponibilidadePorTipo.isEmpty())
				req.setAttribute("msgErro", 4);
		}

		return "/reserva.jsp";
	}
}
