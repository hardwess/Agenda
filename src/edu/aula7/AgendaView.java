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
import javax.swing.JTextField;

public class AgendaView implements ActionListener {

	private JTextField txtNome = new JTextField();
	private JTextField txtTelefone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnPesquisar = new JButton("Pesquisar");

	private List<Agenda> lista = new ArrayList<Agenda>();

	public AgendaView() {
		JFrame janela = new JFrame("Agenda Telefonica");
		JPanel panPrincipal = new JPanel(new BorderLayout());
		JPanel panBotoes = new JPanel();
		JPanel panForm = new JPanel(new GridLayout(3, 2));

		btnSalvar.addActionListener(this);
		btnPesquisar.addActionListener(this);

		panBotoes.add(btnSalvar);
		panBotoes.add(btnPesquisar);
		panForm.add(new JLabel("Nome"));
		panForm.add(txtNome);
		panForm.add(new JLabel("Telefone"));
		panForm.add(txtTelefone);
		panForm.add(new JLabel("Email"));
		panForm.add(txtEmail);
		panPrincipal.add(panForm, BorderLayout.CENTER);
		panPrincipal.add(panBotoes, BorderLayout.SOUTH);
		janela.setContentPane(panPrincipal);
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

			lista.add(a);
			System.out.println("Agenda gravada, agora a lista possui " + lista.size() + " objetos");
		} else if ("Pesquisar".equals(cmd)) {
			for (Agenda a : lista) {
				if (a.getNome().contains(txtNome.getText())) {
					txtNome.setText(a.getNome());
					txtTelefone.setText(a.getTelefone());
					txtEmail.setText(a.getEmail());
				}
			}
		}
	}
}
