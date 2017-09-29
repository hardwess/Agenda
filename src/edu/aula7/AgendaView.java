package edu.aula7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AgendaView implements ActionListener, TableModel {
	
	private JTextField txtNome = new JTextField();
	private JTextField txtTelefone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnPesquisar = new JButton("Pesquisar");
	private JTable tabela = new JTable();
	
	private List<Agenda> lista = new ArrayList<Agenda>();
	
	public AgendaView() { 
		JFrame janela = new JFrame("Agenda Telefonica");
		JPanel panPrincipal = new JPanel(new BorderLayout());
		JPanel panBotoes = new JPanel();
		JPanel panForm = new JPanel(new GridLayout(3, 2));
		
		btnSalvar.addActionListener( this );
		btnPesquisar.addActionListener( this );
		
		tabela.setModel( this );
		
		panBotoes.add(btnSalvar);
		panBotoes.add(btnPesquisar);
		panForm.add(new JLabel("Nome"));
		panForm.add(txtNome);
		panForm.add(new JLabel("Telefone"));
		panForm.add(txtTelefone);
		panForm.add(new JLabel("Email"));
		panForm.add(txtEmail);
		panPrincipal.add(panForm, BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(tabela);
		
		panPrincipal.add(scroll, BorderLayout.CENTER);
		panPrincipal.add(panBotoes, BorderLayout.SOUTH);
		janela.setContentPane( panPrincipal );
		janela.setSize(400, 250);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	public static void main(String[] args) {
		new AgendaView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Salvar".equals(cmd)) {
			Agenda a = new Agenda();
			a.setNome(txtNome.getText());
			a.setTelefone(txtTelefone.getText());
			a.setEmail(txtEmail.getText());
			lista.add( a );	
			tabela.invalidate();
			tabela.revalidate();
			tabela.repaint();
			System.out.println("Agenda gravada, agora a lista tem " + lista.size() + " objetos");
		} else if ("Pesquisar".equals(cmd)) { 
			for( Agenda a : lista ) { 
				if (a.getNome().contains( txtNome.getText() )) { 
					txtNome.setText( a.getNome() );
					txtTelefone.setText( a.getTelefone() );
					txtEmail.setText( a.getEmail() );
				}
			}
		}
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		switch( coluna ) { 
			case 0 : return String.class;
			case 1 : return String.class;
			case 2 : return String.class;
		}
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int coluna) {
		switch( coluna ) { 
			case 0 : return "Nome";
			case 1 : return "Telefone";
			case 2 : return "Email";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Agenda a = lista.get( linha );
		switch( coluna ) { 
			case 0 : return a.getNome();
			case 1 : return a.getTelefone();
			case 2 : return a.getEmail();
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}